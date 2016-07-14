/*
 * Copyright 2016  VRTECH.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pub.vrtech.transport.transports;

import java.net.InetSocketAddress;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import pub.vrtech.common.Constants;
import pub.vrtech.common.URL;
import pub.vrtech.common.logs.Logger;
import pub.vrtech.common.utils.NamedThreadFactory;
import pub.vrtech.common.utils.NetUtils;
import pub.vrtech.transport.AbstractEndpoint;
import pub.vrtech.transport.Channel;
import pub.vrtech.transport.ChannelHandler;
import pub.vrtech.transport.Client;
import pub.vrtech.transport.RemotingException;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public abstract class AbstractClient extends AbstractEndpoint implements Client {

    /***
     * logger
     */
    private static final Logger logger = pub.vrtech.common.logs.LoggerFactory
            .getLogger(AbstractClient.class);

//    private static final AtomicInteger CLIENT_THREAD_POOL_ID = new AtomicInteger();

    private final Lock connectLock = new ReentrantLock();

    private static final ScheduledThreadPoolExecutor reconnectExecutorService = new ScheduledThreadPoolExecutor(
            2, new NamedThreadFactory("MonkeyClientReconnectTimer", true));

    private volatile ScheduledFuture<?> reconnectExecutorFuture = null;

//    protected volatile ExecutorService executor;

    private final boolean send_reconnect;

    private final AtomicInteger reconnect_count = new AtomicInteger(0);

    // 重连的error日志是否已经被调用过.
    private final AtomicBoolean reconnect_error_log_flag = new AtomicBoolean(
            false);

    // 重连warning的间隔.(waring多少次之后，warning一次) //for test
    private final int reconnect_warning_period;

    // the last successed connected time
    private long lastConnectedTime = System.currentTimeMillis();

    /**
     * 关闭超时时间
     */
    private final long shutdown_timeout;

    /**
     * @param url
     * @param handler
     * @throws RemotingException
     */
    public AbstractClient(URL url, ChannelHandler handler)
            throws RemotingException {
        super(url, handler);
        send_reconnect = url.getParameter(Constants.SEND_RECONNECT_KEY, false);

        shutdown_timeout = url.getParameter(Constants.SHUTDOWN_TIMEOUT_KEY,
                Constants.DEFAULT_SHUTDOWN_TIMEOUT);
        // 默认重连间隔2s，1800表示1小时warning一次.
        reconnect_warning_period = url.getParameter("reconnect.waring.period",
                1800);

        // 打开handler
        try {
            doOpen();
        } catch (Throwable t) {
            close();
            throw new RemotingException(url.toInetSocketAddress(), null,
                    "Failed to start " + getClass().getSimpleName() + " "
                            + NetUtils.getLocalAddress()
                            + " connect to the server " + getRemoteAddress()
                            + ", cause: " + t.getMessage(), t);
        }
        try {
            connect();
            if (logger.isInfoEnabled()) {
                logger.info("Start " + getClass().getSimpleName() + " "
                        + NetUtils.getLocalAddress()
                        + " connect to the server " + getRemoteAddress());
            }
        } catch (RemotingException t) {
            if (url.getParameter(Constants.CHECK_KEY, true)) {
                close();
                throw t;
            } else {
                logger.warn("Failed to start " + getClass().getSimpleName()
                        + " " + NetUtils.getLocalAddress()
                        + " connect to the server " + getRemoteAddress()
                        + " (check == false, ignore and retry later!), cause: "
                        + t.getMessage(), t);
            }
        } catch (Throwable t) {
            close();
            throw new RemotingException(url.toInetSocketAddress(), null,
                    "Failed to start " + getClass().getSimpleName() + " "
                            + NetUtils.getLocalAddress()
                            + " connect to the server " + getRemoteAddress()
                            + ", cause: " + t.getMessage(), t);
        }
//        executor = 

    }

    private static int getReconnectParam(URL url) {
        int reconnect;
        String param = url.getParameter(Constants.RECONNECT_KEY);
        if (param == null || param.length() == 0
                || "true".equalsIgnoreCase(param)) {
            reconnect = Constants.DEFAULT_RECONNECT_PERIOD;
        } else if ("false".equalsIgnoreCase(param)) {
            reconnect = 0;
        } else {
            try {
                reconnect = Integer.parseInt(param);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "reconnect param must be nonnegative integer or false/true. input is:"
                                + param);
            }
            if (reconnect < 0) {
                throw new IllegalArgumentException(
                        "reconnect param must be nonnegative integer or false/true. input is:"
                                + param);
            }
        }
        return reconnect;
    }
    /**
     * init reconnect thread
     */
    private synchronized void initConnectStatusCheckCommand() {
        int reconnect = getReconnectParam(getUrl());
        if (reconnect > 0
                && (reconnectExecutorFuture == null || reconnectExecutorFuture
                        .isCancelled())) {
            Runnable connectStatusCheckCommand = new Runnable() {
                public void run() {
                    try {
                        if (!isConnected()) {
                            connect();
                        } else {
                            lastConnectedTime = System.currentTimeMillis();
                        }
                    } catch (Throwable t) {
                        String errorMsg = "client reconnect to "
                                + getUrl().getAddress() + " find error . url: "
                                + getUrl();
                        // wait registry sync provider list
                        if (System.currentTimeMillis() - lastConnectedTime > shutdown_timeout) {
                            if (!reconnect_error_log_flag.get()) {
                                reconnect_error_log_flag.set(true);
                                logger.error(errorMsg, t);
                                return;
                            }
                        }
                        if (reconnect_count.getAndIncrement()
                                % reconnect_warning_period == 0) {
                            logger.warn(errorMsg, t);
                        }
                    }
                }
            };
            reconnectExecutorFuture = reconnectExecutorService
                    .scheduleWithFixedDelay(connectStatusCheckCommand,
                            reconnect, reconnect, TimeUnit.MILLISECONDS);
        }
    }

    protected void connect() throws RemotingException {
        connectLock.lock();
        try {
            if (isConnected()) {
                return;
            }
            initConnectStatusCheckCommand();
            doConnect();
            if (!isConnected()) {
                throw new RemotingException(this, "Failed connect to server "
                        + getRemoteAddress() + " from "
                        + getClass().getSimpleName() + " "
                        + NetUtils.getLocalHost()
                        + ", cause: Connect wait timeout: " + getTimeout()
                        + "ms.");
            } else {
                if (logger.isInfoEnabled()) {
                    logger.info("Successed connect to server "
                            + getRemoteAddress() + " from "
                            + getClass().getSimpleName() + " "
                            + NetUtils.getLocalHost() + ", channel is "
                            + this.getChannel());
                }
            }
            reconnect_count.set(0);
            reconnect_error_log_flag.set(false);
        } catch (RemotingException e) {
            throw e;
        } catch (Throwable e) {
            throw new RemotingException(this, "Failed connect to server "
                    + getRemoteAddress() + " from "
                    + getClass().getSimpleName() + " "
                    + NetUtils.getLocalHost() + ", cause: " + e.getMessage(), e);
        } finally {
            connectLock.unlock();
        }
    }

    protected int getTimeout() {
        return timeout;
    }

    protected int getConnectTimeout() {
        return connectTimeout;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pub.vrtech.transport.Endpoint#getLocalAddress()
     */
    @Override
    public InetSocketAddress getLocalAddress() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pub.vrtech.transport.Endpoint#send(java.lang.Object, boolean)
     */
    @Override
    public void send(Object message, boolean sent) throws RemotingException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see pub.vrtech.transport.Channel#getRemoteAddress()
     */
    @Override
    public InetSocketAddress getRemoteAddress() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pub.vrtech.transport.Channel#isConnected()
     */
    @Override
    public boolean isConnected() {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pub.vrtech.transport.Channel#hasAttribute(java.lang.String)
     */
    @Override
    public boolean hasAttribute(String key) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pub.vrtech.transport.Channel#getAttribute(java.lang.String)
     */
    @Override
    public Object getAttribute(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pub.vrtech.transport.Channel#setAttribute(java.lang.String,
     * java.lang.Object)
     */
    @Override
    public void setAttribute(String key, Object value) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see pub.vrtech.transport.Channel#removeAttribute(java.lang.String)
     */
    @Override
    public void removeAttribute(String key) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see pub.vrtech.transport.Client#reconnect()
     */
    @Override
    public void reconnect() throws RemotingException {
        // TODO Auto-generated method stub

    }

    /**
     * Open client.
     * 
     * @throws Throwable
     */
    protected abstract void doOpen() throws Throwable;

    /**
     * Close client.
     * 
     * @throws Throwable
     */
    protected abstract void doClose() throws Throwable;

    /**
     * Connect to server.
     * 
     * @throws Throwable
     */
    protected abstract void doConnect() throws Throwable;

    /**
     * disConnect to server.
     * 
     * @throws Throwable
     */
    protected abstract void doDisConnect() throws Throwable;

    /**
     * Get the connected channel.
     * 
     * @return channel
     */
    protected abstract Channel getChannel();

}

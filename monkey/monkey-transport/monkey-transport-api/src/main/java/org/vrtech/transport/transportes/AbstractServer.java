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
package org.vrtech.transport.transportes;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.concurrent.ExecutorService;

import org.vrtech.common.Constants;
import org.vrtech.common.URL;
import org.vrtech.common.logs.Logger;
import org.vrtech.common.logs.LoggerFactory;
import org.vrtech.common.utils.NetUtils;
import org.vrtech.transport.AbstractEndpoint;
import org.vrtech.transport.AbstractPeer;
import org.vrtech.transport.Channel;
import org.vrtech.transport.ChannelHandler;
import org.vrtech.transport.RemotingException;
import org.vrtech.transport.Server;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public abstract class AbstractServer extends AbstractEndpoint implements Server {

    private static final Logger logger = LoggerFactory
            .getLogger(AbstractServer.class);

    private InetSocketAddress localAddress;

    private InetSocketAddress bindAddress;

    private int accepts;

    private int idleTimeout = 600;

    protected static final String SERVER_THREAD_POOL_NAME = "MonkeyServerHandler";

    ExecutorService executor;

    public AbstractServer(URL url, ChannelHandler handler)
            throws RemotingException {
        super(url, handler);
        localAddress = getUrl().toInetSocketAddress();
        String host = url.getParameter(Constants.ANYHOST_KEY, false)
                || NetUtils.isInvalidLocalHost(getUrl().getHost())
                ? NetUtils.ANYHOST
                : getUrl().getHost();
        bindAddress = new InetSocketAddress(host, getUrl().getPort());
        this.accepts = url.getParameter(Constants.ACCEPTS_KEY,
                Constants.DEFAULT_ACCEPTS);
        this.idleTimeout = url.getParameter(Constants.IDLE_TIMEOUT_KEY,
                Constants.DEFAULT_IDLE_TIMEOUT);
        try {
            doOpen();
            if (logger.isInfoEnabled()) {
                logger.info("Start " + getClass().getSimpleName() + " bind "
                        + getBindAddress() + ", export " + getLocalAddress());
            }
        } catch (Throwable t) {
            throw new RemotingException(url.toInetSocketAddress(), null,
                    "Failed to bind " + getClass().getSimpleName() + " on "
                            + getLocalAddress() + ", cause: " + t.getMessage(),
                    t);
        }
        // if (handler instanceof WrappedChannelHandler) {
        // executor = ((WrappedChannelHandler) handler).getExecutor();
        // }
    }

    protected abstract void doOpen() throws Throwable;

    protected abstract void doClose() throws Throwable;

    public InetSocketAddress getLocalAddress() {
        return localAddress;
    }

    public InetSocketAddress getBindAddress() {
        return bindAddress;
    }

    public int getAccepts() {
        return accepts;
    }

    public int getIdleTimeout() {
        return idleTimeout;
    }

    @Override
    public void connected(Channel ch) throws RemotingException {
        Collection<Channel> channels = getChannels();
        if (accepts > 0 && channels.size() > accepts) {
            logger.error("Close channel " + ch + ", cause: The server "
                    + ch.getLocalAddress()
                    + " connections greater than max config " + accepts);
            ch.close();
            return;
        }
        super.connected(ch);
    }

    @Override
    public void disconnected(Channel ch) throws RemotingException {
        Collection<Channel> channels = getChannels();
        if (channels.size() == 0) {
            logger.warn("All clients has discontected from "
                    + ch.getLocalAddress() + ". You can graceful shutdown now.");
        }
        super.disconnected(ch);
    }
}

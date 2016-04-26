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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pub.vrtech.common.URL;
import pub.vrtech.common.logs.Logger;
import pub.vrtech.common.logs.LoggerFactory;
import pub.vrtech.common.utils.NamedThreadFactory;
import pub.vrtech.transport.Channel;
import pub.vrtech.transport.ChannelHandler;
import pub.vrtech.transport.ChannelHandlerDelegate;
import pub.vrtech.transport.RemotingException;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public class WrappedChannelHandler implements ChannelHandlerDelegate {

    protected static final Logger logger = LoggerFactory
            .getLogger(WrappedChannelHandler.class);

    protected static final ExecutorService SHARED_EXECUTOR = Executors
            .newCachedThreadPool(new NamedThreadFactory("DubboSharedHandler",
                    true));

    protected final ExecutorService executor;

    protected final ChannelHandler handler;

    protected final URL url;

    public WrappedChannelHandler(ChannelHandler handler, URL url) {
        this.handler = handler;
        this.url = url;
        executor = null;
    }

    public void close() {
        try {
            if (executor instanceof ExecutorService) {
                ((ExecutorService) executor).shutdown();
            }
        } catch (Throwable t) {
            logger.warn(
                    "fail to destroy thread pool of server: " + t.getMessage(),
                    t);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pub.vrtech.transport.ChannelHandler#connected(pub.vrtech.transport.Channel
     * )
     */
    @Override
    public void connected(Channel channel) throws RemotingException {
        handler.connected(channel);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pub.vrtech.transport.ChannelHandler#disconnected(pub.vrtech.transport
     * .Channel)
     */
    @Override
    public void disconnected(Channel channel) throws RemotingException {
        handler.disconnected(channel);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pub.vrtech.transport.ChannelHandler#sent(pub.vrtech.transport.Channel,
     * java.lang.Object)
     */
    @Override
    public void sent(Channel channel, Object message) throws RemotingException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pub.vrtech.transport.ChannelHandler#received(pub.vrtech.transport.Channel
     * , java.lang.Object)
     */
    @Override
    public void received(Channel channel, Object message)
            throws RemotingException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pub.vrtech.transport.ChannelHandler#caught(pub.vrtech.transport.Channel,
     * java.lang.Throwable)
     */
    @Override
    public void caught(Channel channel, Throwable exception)
            throws RemotingException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see pub.vrtech.transport.ChannelHandlerDelegate#getHandler()
     */
    @Override
    public ChannelHandler getHandler() {
        // TODO Auto-generated method stub
        return null;
    }

}

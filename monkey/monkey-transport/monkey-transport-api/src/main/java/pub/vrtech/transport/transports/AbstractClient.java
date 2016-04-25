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

import pub.vrtech.common.URL;
import pub.vrtech.common.logs.Logger;
import pub.vrtech.transport.AbstractEndpoint;
import pub.vrtech.transport.ChannelHandler;
import pub.vrtech.transport.Client;
import pub.vrtech.transport.RemotingException;

/**
 *
 * Function description：
 * 1.XXX
 * 2.XXX
 * @author houge
 */
public abstract class AbstractClient extends AbstractEndpoint implements Client {
    
    private static final Logger logger=pub.vrtech.common.logs.LoggerFactory.getLogger(AbstractClient.class);

    
    /**
     * @param url
     * @param handler
     */
    public AbstractClient(URL url, ChannelHandler handler) {
        super(url, handler);
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see pub.vrtech.transport.Endpoint#getLocalAddress()
     */
    @Override
    public InetSocketAddress getLocalAddress() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see pub.vrtech.transport.Endpoint#send(java.lang.Object, boolean)
     */
    @Override
    public void send(Object message, boolean sent) throws RemotingException {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see pub.vrtech.transport.Channel#getRemoteAddress()
     */
    @Override
    public InetSocketAddress getRemoteAddress() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see pub.vrtech.transport.Channel#isConnected()
     */
    @Override
    public boolean isConnected() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see pub.vrtech.transport.Channel#hasAttribute(java.lang.String)
     */
    @Override
    public boolean hasAttribute(String key) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see pub.vrtech.transport.Channel#getAttribute(java.lang.String)
     */
    @Override
    public Object getAttribute(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see pub.vrtech.transport.Channel#setAttribute(java.lang.String, java.lang.Object)
     */
    @Override
    public void setAttribute(String key, Object value) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see pub.vrtech.transport.Channel#removeAttribute(java.lang.String)
     */
    @Override
    public void removeAttribute(String key) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see pub.vrtech.transport.Client#reconnect()
     */
    @Override
    public void reconnect() throws RemotingException {
        // TODO Auto-generated method stub

    }

}

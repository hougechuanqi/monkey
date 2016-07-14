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
package pub.vrtech.monkey.transport.netty;

import pub.vrtech.common.URL;
import pub.vrtech.transport.Channel;
import pub.vrtech.transport.ChannelHandler;
import pub.vrtech.transport.RemotingException;
import pub.vrtech.transport.transports.AbstractClient;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public class NettyClient extends AbstractClient {


    /**
     * @param url
     * @param handler
     * @throws RemotingException
     */
    public NettyClient(URL url, ChannelHandler handler)
            throws RemotingException {
        super(url, handler);
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see pub.vrtech.transport.transports.AbstractClient#doOpen()
     */
    @Override
    protected void doOpen() throws Throwable {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see pub.vrtech.transport.transports.AbstractClient#doClose()
     */
    @Override
    protected void doClose() throws Throwable {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see pub.vrtech.transport.transports.AbstractClient#doConnect()
     */
    @Override
    protected void doConnect() throws Throwable {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see pub.vrtech.transport.transports.AbstractClient#doDisConnect()
     */
    @Override
    protected void doDisConnect() throws Throwable {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see pub.vrtech.transport.transports.AbstractClient#getChannel()
     */
    @Override
    protected Channel getChannel() {
        // TODO Auto-generated method stub
        return null;
    }

}

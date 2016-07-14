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
import pub.vrtech.transport.ChannelHandler;
import pub.vrtech.transport.Client;
import pub.vrtech.transport.RemotingException;
import pub.vrtech.transport.Server;
import pub.vrtech.transport.TransportTypes;
import pub.vrtech.transport.Transporter;

/**
 *
 * Function description：
 * 
 * 
 * @author houge
 */
public class NettyTransporter implements Transporter {

    /*
     * (non-Javadoc)
     * 
     * @see pub.vrtech.transport.Transporter#getTransportType()
     */
    public TransportTypes getTransportType() {
        return TransportTypes.NETTY;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pub.vrtech.transport.Transporter#bind(java.lang.String,
     * pub.vrtech.transport.ChannelHandler)
     */
    public Server bind(String url, ChannelHandler listener)
            throws RemotingException {
        return new NettyServer(URL.valueOf(url), listener);
    }

    /*
     * (non-Javadoc)
     * 
     * @see pub.vrtech.transport.Transporter#connect(java.lang.String,
     * pub.vrtech.transport.ChannelHandler)
     */
    public Client connect(String url, ChannelHandler listener) throws RemotingException {
        return new NettyClient(URL.valueOf(url), listener);
    }

}

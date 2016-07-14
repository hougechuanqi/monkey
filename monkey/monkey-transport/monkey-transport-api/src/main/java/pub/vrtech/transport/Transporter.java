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
package pub.vrtech.transport;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public interface Transporter {

    /***
     * 获得transport类型
     * 
     * @return
     */
    public TransportTypes getTransportType();

    /***
     * 服务器绑定本地服务端口号
     * 
     * @param url
     *            绑定URL地址
     * @param handler
     *            IO处理handler
     * @return
     * @throws RemotingException
     */
    public Server bind(String url, ChannelHandler handler)
            throws RemotingException;

    /***
     * 客户端连接服务器
     * 
     * @param url
     *            连接URL地址
     * @param handler
     *            IO处理handler
     * @return
     */
    public Client connect(String url, ChannelHandler handler)throws RemotingException ;

}

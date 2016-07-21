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
package pub.vrtech.protocol;

import pub.vrrech.monkey.rpc.api.RpcDecoder;
import pub.vrrech.monkey.rpc.api.RpcExchanger;
import pub.vrtech.transport.Channel;
import pub.vrtech.transport.Decodeable;
import pub.vrtech.transport.transports.Packet;

/**
 *
 * 
 * 
 * 
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public class RedisRpcDecoder implements RpcDecoder {

    /*
     * (non-Javadoc)
     * 
     * @see
     * pub.vrrech.monkey.rpc.api.RpcDecoder#decode(java.nio.channels.Channel,
     * pub.vrtech.transport.Decodeable)
     */
    @Override
    public RpcExchanger decode(java.nio.channels.Channel channel, Decodeable msg) {
        if (msg instanceof RedisCommand) {// 如果是RedisCommand
            

        }

        return null;
    }

}

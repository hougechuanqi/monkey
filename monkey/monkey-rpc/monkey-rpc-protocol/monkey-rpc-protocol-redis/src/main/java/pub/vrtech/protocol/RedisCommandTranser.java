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

import pub.vrrech.monkey.rpc.api.RpcInvocation;
import pub.vrtech.transport.Decodeable;
import pub.vrtech.transport.transports.AbstractProtocolTransfer;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public class RedisCommandTranser extends AbstractProtocolTransfer {

    /*
     * (non-Javadoc)
     * 
     * @see
     * pub.vrtech.transport.transports.AbstractProtocolTransfer#doTransfer(pub
     * .vrtech.transport.Decodeable)
     */
    @Override
    protected RpcInvocation doTransfer(Decodeable decodeable) {
        RedisCommand cmd = (RedisCommand) decodeable;
        

        return null;
    }

}

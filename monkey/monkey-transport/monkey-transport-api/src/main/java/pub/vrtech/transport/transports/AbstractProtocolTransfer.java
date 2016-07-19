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

import pub.vrrech.monkey.rpc.api.RpcInvocation;
import pub.vrtech.common.logs.Logger;
import pub.vrtech.common.logs.LoggerFactory;
import pub.vrtech.transport.Decodeable;

/**
 *
 * Function description： 抽象协议转换器，用于转换redis命令和二进制命令到RPCInvocation封装 1.XXX 2.XXX
 * 
 * @author houge
 */
public abstract class AbstractProtocolTransfer implements ProtocalTransfor {

    protected final static Logger logger = LoggerFactory
            .getLogger(AbstractProtocolTransfer.class);

    @Override
    public RpcInvocation transfer(Decodeable decodeable) {
        return doTransfer(decodeable);
    }

    protected abstract RpcInvocation doTransfer(Decodeable decodeable);

}

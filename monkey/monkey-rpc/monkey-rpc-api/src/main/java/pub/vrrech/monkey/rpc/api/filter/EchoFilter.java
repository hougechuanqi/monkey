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
package pub.vrrech.monkey.rpc.api.filter;

import pub.vrrech.monkey.rpc.api.Filter;
import pub.vrrech.monkey.rpc.api.Invocation;
import pub.vrrech.monkey.rpc.api.Invoker;
import pub.vrrech.monkey.rpc.api.Result;
import pub.vrrech.monkey.rpc.api.RpcResult;
import pub.vrrech.monkey.rpc.api.exception.RpcException;
import pub.vrtech.common.Constants;

/**
 *
 * Function description：
 * 1.XXX
 * 2.XXX
 * @author houge
 */
public class EchoFilter implements Filter {

    /* (non-Javadoc)
     * @see pub.vrrech.monkey.rpc.api.Filter#invoke(pub.vrrech.monkey.rpc.api.Invoker, pub.vrrech.monkey.rpc.api.Invocation)
     */
    @Override
    public Result invoke(Invoker<?> invoker, Invocation inv)
            throws RpcException {
        if(inv.getMethodName().equals(Constants.$ECHO) && inv.getArguments() != null && inv.getArguments().length == 1 )
            return new RpcResult(inv.getArguments()[0]);
        return invoker.invoke(inv);
    }

}

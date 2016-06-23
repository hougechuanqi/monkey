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
import pub.vrrech.monkey.rpc.api.RpcContext;
import pub.vrrech.monkey.rpc.api.RpcInvocation;
import pub.vrrech.monkey.rpc.api.exception.RpcException;
import pub.vrtech.common.utils.NetUtils;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public class ConsumerContextFilter implements Filter {

    /*
     * (non-Javadoc)
     * 
     * @see
     * pub.vrrech.monkey.rpc.api.Filter#invoke(pub.vrrech.monkey.rpc.api.Invoker
     * , pub.vrrech.monkey.rpc.api.Invocation)
     */
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation)
            throws RpcException {
        RpcContext
                .getContext()
                .setInvoker(invoker)
                .setInvocation(invocation)
                .setLocalAddress(NetUtils.getLocalHost(), 0)
                .setRemoteAddress(invoker.getUrl().getHost(),
                        invoker.getUrl().getPort());
        if (invocation instanceof RpcInvocation) {
            ((RpcInvocation) invocation).setInvoker(invoker);
        }
        try {
            return invoker.invoke(invocation);
        } finally {
            RpcContext.getContext().clearAttachments();
        }
    }

}

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
package pub.vrrech.monkey.rpc.api.proxy;

import pub.vrrech.monkey.rpc.api.Invoker;
import pub.vrrech.monkey.rpc.api.ProxyFactory;
import pub.vrrech.monkey.rpc.api.exception.RpcException;
import pub.vrtech.common.URL;

/**
 *
 * Function description：
 * 1.XXX
 * 2.XXX
 * @author houge
 */
public class AbstractProxyFactory implements ProxyFactory{

    /* (non-Javadoc)
     * @see pub.vrrech.monkey.rpc.api.ProxyFactory#getProxy(pub.vrrech.monkey.rpc.api.Invoker)
     */
    @Override
    public <T> T getProxy(Invoker<T> invoker) throws RpcException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see pub.vrrech.monkey.rpc.api.ProxyFactory#getInvoker(java.lang.Object, java.lang.Class, pub.vrtech.common.URL)
     */
    @Override
    public <T> Invoker<T> getInvoker(T proxy, Class<T> type, URL url)
            throws RpcException {
        // TODO Auto-generated method stub
        return null;
    }

}

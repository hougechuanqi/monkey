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
package pub.vrrech.monkey.rpc.api.invoker;

import java.lang.reflect.InvocationTargetException;

import pub.vrrech.monkey.rpc.api.Invocation;
import pub.vrrech.monkey.rpc.api.Invoker;
import pub.vrrech.monkey.rpc.api.Result;
import pub.vrrech.monkey.rpc.api.RpcInvocation;
import pub.vrrech.monkey.rpc.api.RpcResult;
import pub.vrrech.monkey.rpc.api.exception.RpcException;
import pub.vrtech.common.URL;
import pub.vrtech.common.logs.Logger;
import pub.vrtech.common.logs.LoggerFactory;
import pub.vrtech.common.utils.NetUtils;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public abstract class AbstractInvoker<T> implements Invoker<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final Class<T> type;

    private final URL url;

    private volatile boolean available = true;

    private volatile boolean destroyed = false;

    public AbstractInvoker(Class<T> type, URL url) {
        if (type == null)
            throw new IllegalArgumentException("service type == null");
        if (url == null)
            throw new IllegalArgumentException("service url == null");
        this.type = type;
        this.url = url;
    }
    public Class<T> getInterface() {
        return type;
    }

    public URL getUrl() {
        return url;
    }

    public boolean isAvailable() {
        return available;
    }

    protected void setAvailable(boolean available) {
        this.available = available;
    }

    public void destroy() {
        if (isDestroyed()) {
            return;
        }
        destroyed = true;
        setAvailable(false);
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public String toString() {
        return getInterface() + " -> "
                + (getUrl() == null ? "" : getUrl().toString());
    }

    public Result invoke(Invocation inv) throws RpcException {
        if (destroyed) {
            throw new RpcException("Rpc invoker for service " + this
                    + " on consumer " + NetUtils.getLogHost()
                    + " is DESTROYED, can not be invoked any more!");
        }
        RpcInvocation invocation = (RpcInvocation) inv;
        invocation.setInvoker(this);
        // if (getUrl().getMethodParameter(invocation.getMethodName(),
        // Constants.ASYNC_KEY, false)){
        // invocation.setAttachment(Constants.ASYNC_KEY,
        // Boolean.TRUE.toString());
        // }
        // RpcUtils.attachInvocationIdIfAsync(getUrl(), invocation);
        try {
            return doInvoke(invocation);
        } catch (InvocationTargetException e) { // biz exception
            Throwable te = e.getTargetException();
            if (te == null) {
                return new RpcResult(e);
            } else {
                if (te instanceof RpcException) {
                    ((RpcException) te).setCode(RpcException.BIZ_EXCEPTION);
                }
                return new RpcResult(te);
            }
        } catch (RpcException e) {
            if (e.isBiz()) {
                return new RpcResult(e);
            } else {
                throw e;
            }
        } catch (Throwable e) {
            return new RpcResult(e);
        }
    }

    protected abstract Result doInvoke(Invocation invocation) throws Throwable;

}

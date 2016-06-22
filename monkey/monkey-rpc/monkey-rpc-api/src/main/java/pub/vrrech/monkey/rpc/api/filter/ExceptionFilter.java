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

import java.lang.reflect.Method;

import pub.vrrech.monkey.rpc.api.Filter;
import pub.vrrech.monkey.rpc.api.Invocation;
import pub.vrrech.monkey.rpc.api.Invoker;
import pub.vrrech.monkey.rpc.api.Result;
import pub.vrrech.monkey.rpc.api.RpcContext;
import pub.vrrech.monkey.rpc.api.RpcResult;
import pub.vrrech.monkey.rpc.api.exception.RpcException;
import pub.vrtech.common.logs.Logger;
import pub.vrtech.common.logs.LoggerFactory;
import pub.vrtech.common.utils.ReflectUtils;
import pub.vrtech.common.utils.StringUtils;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public class ExceptionFilter implements Filter {

    private final Logger logger;

    public ExceptionFilter() {
        this(LoggerFactory.getLogger(ExceptionFilter.class));
    }

    public ExceptionFilter(Logger logger) {
        this.logger = logger;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pub.vrrech.monkey.rpc.api.Filter#invoke(pub.vrrech.monkey.rpc.api.Invoker
     * , pub.vrrech.monkey.rpc.api.Invocation)
     */
    @Override
    public Result invoke(Invoker<?> invoker, Invocation inv)
            throws RpcException {
        try {
            Result result = invoker.invoke(inv);
            if (result.hasException()) {
                try {
                    Throwable exception = result.getException();
                    // 如果是checked异常，直接抛出
                    if (!(exception instanceof RuntimeException)
                            && (exception instanceof Exception)) {
                        return result;
                    }
                    // 在方法签名上有声明，直接抛出
                    try {
                        Method method = invoker.getInterface().getMethod(
                                inv.getMethodName(), inv.getParameterTypes());
                        Class<?>[] exceptionClassses = method
                                .getExceptionTypes();
                        for (Class<?> exceptionClass : exceptionClassses) {
                            if (exception.getClass().equals(exceptionClass)) {
                                return result;
                            }
                        }
                    } catch (NoSuchMethodException e) {
                        return result;
                    }
                    // 未在方法签名上定义的异常，在服务器端打印ERROR日志
                    logger.error(
                            "Got unchecked and undeclared exception which called by "
                                    + RpcContext.getContext().getRemoteHost()
                                    + ". service: "
                                    + invoker.getInterface().getName()
                                    + ", method: " + inv.getMethodName()
                                    + ", exception: "
                                    + exception.getClass().getName() + ": "
                                    + exception.getMessage(), exception);
                    // 异常类和接口类在同一jar包里，直接抛出
                    String serviceFile = ReflectUtils.getCodeBase(invoker
                            .getInterface());
                    String exceptionFile = ReflectUtils.getCodeBase(exception
                            .getClass());
                    if (serviceFile == null || exceptionFile == null
                            || serviceFile.equals(exceptionFile)) {
                        return result;
                    }
                    // 是JDK自带的异常，直接抛出
                    String className = exception.getClass().getName();
                    if (className.startsWith("java.")
                            || className.startsWith("javax.")) {
                        return result;
                    }
                    // 是Monkey本身的异常，直接抛出
                    if (exception instanceof RpcException) {
                        return result;
                    }
                    // 否则，包装成RuntimeException抛给客户端
                    return new RpcResult(new RuntimeException(
                            StringUtils.toString(exception)));
                } catch (Throwable e) {
                    logger.warn("Fail to ExceptionFilter when called by "
                            + RpcContext.getContext().getRemoteHost()
                            + ". service: " + invoker.getInterface().getName()
                            + ", method: " + inv.getMethodName()
                            + ", exception: " + e.getClass().getName() + ": "
                            + e.getMessage(), e);
                    return result;
                }
            }
            return result;
        } catch (RuntimeException e) {
            logger.error(
                    "Got unchecked and undeclared exception which called by "
                            + RpcContext.getContext().getRemoteHost()
                            + ". service: " + invoker.getInterface().getName()
                            + ", method: " + inv.getMethodName()
                            + ", exception: " + e.getClass().getName() + ": "
                            + e.getMessage(), e);
            throw e;
        }
    }

}

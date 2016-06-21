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
package pub.vrrech.monkey.rpc.api;


/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public class Request {

    private byte magicHigh;
    private byte magicLow;
    private byte version;
    private byte serializationId;
    private long dataId;
    private int dataLength;

    private RpcRequest rpcRequest;

    public Request(final byte magicHigh, final byte magicLow,
            final byte version, final byte serializationId, final long dataId,
            final int dataLength, final RpcRequest rpcRequest) {
        this.magicHigh = magicHigh;
        this.magicLow = magicLow;
        this.version = version;
        this.serializationId = serializationId;
        this.dataId = dataId;
        this.dataLength = dataLength;
        this.rpcRequest = rpcRequest;
    }

    public byte getMagicHigh() {
        return magicHigh;
    }

    public void setMagicHigh(byte magicHigh) {
        this.magicHigh = magicHigh;
    }

    public byte getMagicLow() {
        return magicLow;
    }

    public void setMagicLow(byte magicLow) {
        this.magicLow = magicLow;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public byte getSerializationId() {
        return serializationId;
    }

    public void setSerializationId(byte serializationId) {
        this.serializationId = serializationId;
    }

    public long getDataId() {
        return dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public RpcRequest getRpcRequest() {
        return rpcRequest;
    }

    public void setRpcRequest(RpcRequest rpcRequest) {
        this.rpcRequest = rpcRequest;
    }

    /***
     * RPC请求
     *
     * Function description： 1.XXX 2.XXX
     * 
     * @author houge
     */
    public static class RpcRequest {
        private String serviceUrl;
        private String methodName;
        private String[] arguments;

        public RpcRequest(String serviceUrl, String methodName,
                String[] arguments) {
            this.methodName = methodName;
            this.arguments = arguments;
        }

        public String getMethodName() {
            return methodName;
        }
        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }
        public String[] getArguments() {
            return arguments;
        }
        public void setArguments(String[] arguments) {
            this.arguments = arguments;
        }
        public String getServiceUrl() {
            return serviceUrl;
        }

        public void setServiceUrl(String serviceUrl) {
            this.serviceUrl = serviceUrl;
        }
    }
    
    

}

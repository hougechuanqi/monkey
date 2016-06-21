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
package org.monkey.rpc.api;

import java.util.Map;

import pub.vrrech.monkey.rpc.api.Request;
import pub.vrrech.monkey.rpc.api.Request.RpcRequest;
import pub.vrrech.monkey.rpc.api.constants.RPCConstants;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Object[] arguments = {"hello", 1};
        // RpcRequest rpcRequest = new RpcRequest("/service/name", "test",
        // arguments);
        // Request request = new Request((byte) 0x9, (byte) 0x9, (byte) 0x1,
        // (byte) 0x1, 10, 10, rpcRequest);
        // System.out.println(JSON.toJSON(request));
        Map<Object, Object> pro = Maps.newHashMap();
        pro.put("url", "/action/test");
        Map<Object, Object> params = Maps.newHashMap();
        params.put("args", arguments);
        params.put("method", "test");
        pro.put("params", params);
        String jsonStr = JSON.toJSONString(pro);
        System.out.println(jsonStr);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        final String url = (String) jsonObject.get(RPCConstants.url);
        JSONObject paramsObject = (JSONObject) jsonObject.get(RPCConstants.params);
        final String  method=(String) paramsObject.get(RPCConstants.method);
        final JSONArray  jsonArray=(JSONArray) paramsObject.get(RPCConstants.argument);
        final Object[] argsa=jsonArray.toArray();
        System.out.println();

    }
}

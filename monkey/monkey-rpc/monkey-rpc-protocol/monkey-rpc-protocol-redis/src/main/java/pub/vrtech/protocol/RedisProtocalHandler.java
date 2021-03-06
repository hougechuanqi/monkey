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

import java.nio.charset.Charset;

import pub.vrrech.monkey.rpc.api.constants.RPCConstants;
import pub.vrtech.common.URL;
import pub.vrtech.common.protocol.Protocol;
import pub.vrtech.transport.AbstractChannelHandlerDelegate;
import pub.vrtech.transport.Channel;
import pub.vrtech.transport.ChannelHandler;
import pub.vrtech.transport.ChannelHandlerAdapter;
import pub.vrtech.transport.RemotingException;
import pub.vrtech.transport.transports.ProtocalTransfor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 *
 * Function description： 1.用于解析redis协议RPC过程 2.XXX
 * 
 * @author houge
 */
public class RedisProtocalHandler extends AbstractChannelHandlerDelegate {

    /**
     * @param handler
     */
    protected RedisProtocalHandler(ChannelHandler handler) {
        super(handler);
    }

    @Override
    public void received(Channel channel, Object message)
            throws RemotingException {
        if (message instanceof RedisCommand) {
            RedisCommand cmd = (RedisCommand) message;
            final String key = new String(cmd.getCmdKey(),
                    Charset.forName("UTF-8"));
            JSONObject jsonObject = JSON.parseObject(key);
            final String url = (String) jsonObject.get(RPCConstants.url);
            JSONObject paramsObject = (JSONObject) jsonObject
                    .get(RPCConstants.params);
            final String method = (String) paramsObject
                    .get(RPCConstants.method);
            final JSONArray jsonArray = (JSONArray) paramsObject
                    .get(RPCConstants.argument);
            final Object[] args = jsonArray.toArray();
            URL requetUrl = URL.valueOf(url);// redis://192.168.1.10:8080/service/getMyholder?timeout=30
            

        } else {
            throw new RemotingException(channel, "Redis协议解析错误,非RedisCommand格式");
        }
    }

}

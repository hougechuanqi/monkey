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
package pub.vrtech.transport.handler;

import java.nio.charset.Charset;

import pub.vrtech.common.logs.Logger;
import pub.vrtech.common.logs.LoggerFactory;
import pub.vrtech.protocol.RedisCommand;
import pub.vrtech.transport.Channel;
import pub.vrtech.transport.ChannelHandler;
import pub.vrtech.transport.Decodeable;
import pub.vrtech.transport.transports.DecodeHandler;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * 
 * @author houge
 */
public class MessageTransformerHanlder extends DecodeHandler {

    private final static Logger logger = LoggerFactory
            .getLogger(MessageTransformerHanlder.class);

    /**
     * @param handler
     */
    protected MessageTransformerHanlder(ChannelHandler handler) {
        super(handler);
    }

    @Override
    protected Object doTransferRpcInvocation(Channel channel, Object message) {
        if (message instanceof Decodeable) {
            Decodeable msg = (Decodeable) message;
            switch (msg.getDecodeType()) {
                case REDIS_COMMAND :
                    RedisCommand cmd = (RedisCommand) msg;
                    final String key = new String(cmd.getCmdKey(),
                            Charset.forName("UTF-8"));
                    

                    break;
                case BINARY :

                    break;
                default :
                    break;
            }
        }
        return null;
    }

}

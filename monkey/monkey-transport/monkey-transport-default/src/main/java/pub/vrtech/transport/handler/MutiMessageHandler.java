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

import java.util.List;

import pub.vrtech.transport.AbstractChannelHandlerDelegate;
import pub.vrtech.transport.Channel;
import pub.vrtech.transport.ChannelHandler;
import pub.vrtech.transport.RemotingException;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public class MutiMessageHandler extends AbstractChannelHandlerDelegate {

    /**
     * @param handler
     */
    protected MutiMessageHandler(ChannelHandler handler) {
        super(handler);
    }

    @Override
    public void received(Channel channel, Object message)
            throws RemotingException {
        if (message instanceof List) {
            @SuppressWarnings("unchecked")
            List<Object> listMsg =  (List<Object>) message;
            for (Object msg : listMsg) {
                handler.received(channel, msg);
            }
        } else {
            handler.received(channel, message);
        }
    }

}

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
package pub.vrtech.transport.transports;

import pub.vrtech.common.logs.Logger;
import pub.vrtech.common.logs.LoggerFactory;
import pub.vrtech.transport.AbstractChannelHandlerDelegate;
import pub.vrtech.transport.Channel;
import pub.vrtech.transport.ChannelHandler;
import pub.vrtech.transport.RemotingException;

/**
 *
 * Function description：
 * 1.XXX
 * 2.XXX
 * @author houge
 */
public class DecodeHandler extends AbstractChannelHandlerDelegate {

    private static final Logger log = LoggerFactory.getLogger(DecodeHandler.class);

    public DecodeHandler(ChannelHandler handler) {
        super(handler);
    }

    public void received(Channel channel, Object message) throws RemotingException {
        if (message instanceof Decodeable) {
            decode(message);
        }

        if (message instanceof Request) {
            decode(((Request)message).getData());
        }

        if (message instanceof Response) {
            decode( ((Response)message).getResult());
        }

        handler.received(channel, message);
    }

    private void decode(Object message) {
        if (message != null && message instanceof Decodeable) {
            try {
                ((Decodeable)message).decode();
                if (log.isDebugEnabled()) {
                    log.debug(new StringBuilder(32).append("Decode decodeable message ")
                                  .append(message.getClass().getName()).toString());
                }
            } catch (Throwable e) {
                if (log.isWarnEnabled()) {
                    log.warn(
                        new StringBuilder(32)
                            .append("Call Decodeable.decode failed: ")
                            .append(e.getMessage()).toString(),
                        e);
                }
            } // ~ end of catch
        } // ~ end of if
    } // ~ end of method decode

}
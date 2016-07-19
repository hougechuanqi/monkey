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

import pub.vrtech.common.logs.Logger;
import pub.vrtech.common.logs.LoggerFactory;
import pub.vrtech.transport.AbstractChannelHandlerDelegate;
import pub.vrtech.transport.ChannelHandler;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public class HeartbeatHandler extends AbstractChannelHandlerDelegate {

    private static final Logger logger = LoggerFactory
            .getLogger(HeartbeatHandler.class);

    /**
     * @param handler
     */
    protected HeartbeatHandler(ChannelHandler handler) {
        super(handler);
    }
    
    

}

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
package pub.vrtech.transport;

import pub.vrtech.common.Constants;
import pub.vrtech.common.URL;
import pub.vrtech.common.logs.Logger;
import pub.vrtech.common.logs.LoggerFactory;
import pub.vrtech.common.protocol.Protocol;
import pub.vrtech.protocol.RedisCommandCodec;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public abstract class AbstractEndpoint extends AbstractPeer {

    private final static Logger logger = LoggerFactory
            .getLogger(AbstractEndpoint.class);

    protected Codec codec;

    protected int timeout;
    protected int connectTimeout;

    /**
     * @param url
     * @param handler
     */
    public AbstractEndpoint(URL url, ChannelHandler handler) {
        super(url, handler);
        this.codec = getCodecHandler();
        this.timeout = url.getPositiveParameter(Constants.TIMEOUT_KEY,
                Constants.DEFAULT_TIMEOUT);
        this.connectTimeout = url.getPositiveParameter(
                Constants.CONNECT_TIMEOUT_KEY,
                Constants.DEFAULT_CONNECT_TIMEOUT);
    }

    public Codec getCodec() {
        return codec;
    }

    protected Codec getCodecHandler(URL url) {
        Protocol pro = url.getProtocol();
        Codec codec = null;
        switch (pro) {
            case REDIS :
                codec = new RedisCommandCodec();
                break;

            default :
                break;
        }
        return codec;
    }

}

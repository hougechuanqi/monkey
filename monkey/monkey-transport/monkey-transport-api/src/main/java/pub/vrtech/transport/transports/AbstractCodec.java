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

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.util.List;

import pub.vrtech.common.logs.Logger;
import pub.vrtech.common.logs.LoggerFactory;
import pub.vrtech.transport.Channel;
import pub.vrtech.transport.Codec;
import pub.vrtech.transport.buffer.ChannelBuffer;

/**
 *
 * Function description： 1.抽象编解码器
 * 
 * @author houge
 */
public abstract class AbstractCodec implements Codec {

    private final static Logger logger = LoggerFactory
            .getLogger(AbstractCodec.class);

    protected CodecType codecType;

    /**
     * 
     */
    public AbstractCodec(CodecType codecType) {
        this.codecType = codecType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pub.vrtech.transport.Codec#encode(pub.vrtech.transport.Channel,
     * pub.vrtech.transport.buffer.ChannelBuffer, java.lang.Object)
     */
    @Override
    public void encode(Channel channel, ByteBuf buffer, Object message)
            throws IOException {
        doEncode(channel, buffer, message);
    }

    /*
     * (non-Javadoc)
     * 
     * @see pub.vrtech.transport.Codec#decode(pub.vrtech.transport.Channel,
     * pub.vrtech.transport.buffer.ChannelBuffer)
     */
    @Override
    public void decode(Channel channel, ByteBuf buffer, List<Object> out)
            throws IOException {
         doDecode(channel, buffer,out);
    }

    protected abstract void doDecode(Channel channel, ByteBuf buffer, List<Object> out)
            throws IOException;

    protected abstract void doEncode(Channel channel, ByteBuf buffer,
            Object message) throws IOException;

}

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
package pub.vrtech.monkey.transport.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

import pub.vrtech.common.URL;
import pub.vrtech.transport.ChannelHandler;
import pub.vrtech.transport.Codec;
import pub.vrtech.transport.buffer.ChannelBuffer;
import pub.vrtech.transport.buffer.ChannelBuffers;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public class NettyCodecAdapter {

    private final Codec codec;

    private final InternalDecoder decoder = new InternalDecoder();

    private final InternalEncoder encoder = new InternalEncoder();

    private final URL url;

    private final ChannelHandler handler;

//    private final int bufferSize;

    public NettyCodecAdapter(Codec codec, URL url, ChannelHandler handler) {
        this.codec = codec;
        this.url = url;
        this.handler = handler;
    }

    @SuppressWarnings("rawtypes")
    private class InternalEncoder extends MessageToByteEncoder {
        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.handler.codec.MessageToByteEncoder#encode(io.netty.channel
         * .ChannelHandlerContext, java.lang.Object, io.netty.buffer.ByteBuf)
         */
        @Override
        protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out)
                throws Exception {
            ChannelBuffer buffer = ChannelBuffers.dynamicBuffer(1024);
            NettyChannel channel = NettyChannel.getOrAddChannel(ctx.channel(),
                    url, handler);
            codec.encode(channel, out, msg);
            out.writeBytes(buffer.array());
        }
    }

    private class InternalDecoder extends ByteToMessageDecoder {

        // private ChannelBuffer buffer = ChannelBuffers.EMPTY_BUFFER;
        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.handler.codec.ByteToMessageDecoder#decode(io.netty.channel
         * .ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List)
         */
        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in,
                List<Object> out) throws Exception {
            NettyChannel channel = NettyChannel.getOrAddChannel(ctx.channel(),
                    url, handler);
            codec.decode(channel, in, out);
        }
    }

    public InternalDecoder getDecoder() {
        return decoder;
    }

    public InternalEncoder getEncoder() {
        return encoder;
    }

}

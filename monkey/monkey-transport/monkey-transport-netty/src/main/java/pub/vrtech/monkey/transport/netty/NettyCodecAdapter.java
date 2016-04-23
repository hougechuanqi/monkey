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

import java.io.IOException;
import java.util.List;

import pub.vrtech.common.Constants;
import pub.vrtech.common.URL;
import pub.vrtech.transport.ChannelHandler;
import pub.vrtech.transport.Codec;
import pub.vrtech.transport.buffer.ChannelBuffer;
import pub.vrtech.transport.buffer.ChannelBuffers;
import pub.vrtech.transport.buffer.DynamicChannelBuffer;

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

    private final int bufferSize;

    public NettyCodecAdapter(Codec codec, URL url, ChannelHandler handler) {
        this.codec = codec;
        this.url = url;
        this.handler = handler;
        int b = url.getPositiveParameter(Constants.BUFFER_KEY,
                Constants.DEFAULT_BUFFER_SIZE);
        this.bufferSize = b >= Constants.MIN_BUFFER_SIZE
                && b <= Constants.MAX_BUFFER_SIZE
                ? b
                : Constants.DEFAULT_BUFFER_SIZE;

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
            codec.encode(channel, buffer, msg);
            out.writeBytes(buffer.array());
        }
    }

    private class InternalDecoder extends ByteToMessageDecoder {

        private ChannelBuffer buffer = ChannelBuffers.EMPTY_BUFFER;
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
            ByteBuf input = (ByteBuf) in;
            int readable = input.readableBytes();
            if (readable <= 0) {
                return;
            }
            ChannelBuffer message;
            if (buffer.readable()) {
                if (buffer instanceof DynamicChannelBuffer) {
                    buffer.writeBytes(input.array());
                    message = buffer;
                } else {
                    int size = buffer.readableBytes() + input.readableBytes();
                    message = ChannelBuffers.dynamicBuffer(size > bufferSize
                            ? size
                            : bufferSize);
                    message.writeBytes(buffer, buffer.readableBytes());
                    message.writeBytes(input.array());
                }
            } else {
                message = ChannelBuffers.wrappedBuffer(input.array());
            }

            NettyChannel channel = NettyChannel.getOrAddChannel(ctx.channel(),
                    url, handler);
            Object msg;
            int saveReaderIndex;

            try {
                // decode object.
                do {
                    saveReaderIndex = message.readerIndex();
                    try {
                        msg = codec.decode(channel, message);
                    } catch (IOException e) {
                        buffer = ChannelBuffers.EMPTY_BUFFER;
                        throw e;
                    }
                    if (msg == Codec.DecodeResult.NEED_MORE_INPUT) {
                        message.readerIndex(saveReaderIndex);
                        break;
                    } else {
                        if (saveReaderIndex == message.readerIndex()) {
                            buffer = ChannelBuffers.EMPTY_BUFFER;
                            throw new IOException("Decode without read data.");
                        }
                        if (msg != null) {
                            out.add(msg);
                        }
                    }
                } while (message.readable());
            } finally {
                if (message.readable()) {
                    message.discardReadBytes();
                    buffer = message;
                } else {
                    buffer = ChannelBuffers.EMPTY_BUFFER;
                }
                NettyChannel.removeChannelIfDisconnected(ctx.channel());
            }
        }
    }

    public InternalDecoder getDecoder() {
        return decoder;
    }

    public InternalEncoder getEncoder() {
        return encoder;
    }

}

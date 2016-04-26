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

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.util.List;

import pub.vrtech.transport.Channel;
import pub.vrtech.transport.transports.AbstractCodec;
import pub.vrtech.transport.transports.CodecType;

/**
 *
 * Function description： Redis Codec
 * redis协议不支持inline，只是支持multibulk
 * @author houge
 */
public class RedisCommandDecoder extends AbstractCodec {

    public static final byte CR = '\r';
    public static final byte LF = '\n';
    private static final byte ZERO = '0';
    private static final byte ARRAY_START = '*';

    private static final byte ARGS_START = '$';

    private byte[][] bytes;
    private int arguments = 0;

    /**
     * @param codecType
     */
    public RedisCommandDecoder() {
        super(CodecType.REDIS);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pub.vrtech.transport.transports.AbstractCodec#doDecode(pub.vrtech.transport
     * .Channel, pub.vrtech.transport.buffer.ChannelBuffer)
     */
    @Override
    protected void doDecode(Channel channel, ByteBuf buffer, List<Object> out)
            throws IOException {
        if (bytes != null) {
            int numArgs = bytes.length;
            for (int i = arguments; i < numArgs; i++) {
                buffer.markReaderIndex();
                if (buffer.readByte() == ARGS_START) {
                    final long l = readLength(buffer);
                    if (l < 0) {
                        buffer.resetReaderIndex();
                        return;
                    }
                    int size = (int) l;
                    if (buffer.readableBytes() < (size + 2)){
                        buffer.resetReaderIndex();
                        return;
                    }
                    bytes[i] = new byte[size];
                    buffer.readBytes(bytes[i]);
                    buffer.skipBytes(2);
                    arguments++;
                } else {
                    throw new IOException("Unexpected character");
                }
            }
            try {
                out.add(new RedisCommand(bytes));
            } finally {
                bytes = null;
                arguments = 0;
            }
        } else if (buffer.readByte() == ARRAY_START) {
            buffer.markReaderIndex();
            long length = readLength(buffer);
            if (length > Integer.MAX_VALUE) {
                throw new IllegalArgumentException(
                        "Java only supports arrays up to " + Integer.MAX_VALUE
                                + " in size");
            }
            if (length < 0) {// 需要更多数据
                buffer.resetReaderIndex();
                return;
            }
            final int numArgs = (int) length;
            bytes = new byte[numArgs][];
            doDecode(channel, buffer, out);
        }
    }
    private long readLength(ByteBuf is) throws IOException {
        final int readerIndex = is.readerIndex();
        final int index = is.indexOf(is.readerIndex(), is.writerIndex(), LF);
        byte[] dest = new byte[index - readerIndex - 1];
        is.readBytes(dest);
        is.skipBytes(2);
        int size = 0;
        for (byte b : dest) {
            int value = b - ZERO;
            if (value >= 0 && value < 10) {
                size *= 10;
                size += value;
            } else {
                throw new IOException("Invalid character in integer");
            }
        }
        return size;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pub.vrtech.transport.transports.AbstractCodec#doEncode(pub.vrtech.transport
     * .Channel, io.netty.buffer.ByteBuf, java.lang.Object)
     */
    @Override
    protected void doEncode(Channel channel, ByteBuf buffer, Object message)
            throws IOException {
        
      

    }

}

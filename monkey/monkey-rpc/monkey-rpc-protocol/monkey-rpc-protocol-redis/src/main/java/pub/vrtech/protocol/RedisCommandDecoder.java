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

import pub.vrtech.common.utils.BytesUtils;
import pub.vrtech.transport.Channel;
import pub.vrtech.transport.transports.AbstractCodec;
import pub.vrtech.transport.transports.CodecType;

/**
 *
 * Function description： Redis Codec redis协议不支持inline，只是支持multibulk
 * 
 * @author houge
 */
public class RedisCommandDecoder extends AbstractCodec
        implements
            IRedisProtocol {

    /****
     * byte bucket
     */
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
            for (int i = arguments; i < numArgs; i++) {//开始解析命令行
                buffer.markReaderIndex();//打标记
                if (buffer.readByte() == RespType.BULK_STRING.getFlag()) {
                    final long l = readLength(buffer);//读取命令字符长度
                    if (l < 0) {
                        buffer.resetReaderIndex();
                        return;
                    }
                    int size = (int) l;
                    if (buffer.readableBytes() < (size + 2)) {
                        buffer.resetReaderIndex();
                        return;
                    }
                    bytes[i] = new byte[size];
                    buffer.readBytes(bytes[i]);//读取字符串内容
                    buffer.skipBytes(2);//跳过/r/n
                    arguments++;
                } else {
                    throw new IOException("Unexpected character");
                }
            }
            try {
                out.add(new RedisCommand(bytes));
            } finally {
                //重置bytes数组，
                bytes = null;
                arguments = 0;
            }
        } else if (buffer.readByte() == RespType.ARRAY.getFlag()) {// 读取多行
            buffer.markReaderIndex();// 打标签
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
            bytes = new byte[numArgs][];//行表示命令数目SET KEY  VALUE 那么numArgs就是3
            doDecode(channel, buffer, out);
        }
    }

    /***
     * 读取长度
     * 
     * @param is
     *            bytebuff流
     * @return
     * @throws IOException
     */
    private long readLength(ByteBuf is) throws IOException {
        final int readerIndex = is.readerIndex();
        final int index = is.indexOf(is.readerIndex(), is.writerIndex(), LF);
        byte[] bytesArray = new byte[index - readerIndex - 1];
        is.readBytes(bytesArray);// 读数字
        is.skipBytes(2);// 跳过 /r/n
        return BytesUtils.byteArray2Integer(bytesArray);
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

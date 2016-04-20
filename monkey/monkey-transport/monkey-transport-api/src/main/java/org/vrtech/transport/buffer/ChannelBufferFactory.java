package org.vrtech.transport.buffer;

import java.nio.ByteBuffer;

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 */
public interface ChannelBufferFactory {

    ChannelBuffer getBuffer(int capacity);
    
    ChannelBuffer getBuffer(byte[] array, int offset, int length);
    
    ChannelBuffer getBuffer(ByteBuffer nioBuffer);
    
}

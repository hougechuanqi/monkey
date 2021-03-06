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

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.util.List;

/**
 *
 * Function description： 1.协议编解码接口
 * 
 * @author houge
 */
public interface Codec {

    void encode(Channel channel, ByteBuf buffer, Object message)
            throws IOException;

    void decode(Channel channel, ByteBuf buffer, List<Object> out) throws IOException;

    enum DecodeResult {
        NEED_MORE_INPUT, SKIP_SOME_INPUT
    }

}

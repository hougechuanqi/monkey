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

import org.apache.commons.codec.Charsets;

import io.netty.buffer.ByteBuf;
import pub.vrtech.common.utils.BytesUtils;
import pub.vrtech.transport.Decodeable;
import pub.vrtech.transport.transports.Packet;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public class RedisCommand implements Decodeable {

    public static final byte[] EMPTY_BYTES = new byte[0];

    /****
     * redis命令存储
     */
    private Object[] redisCommands;

    private String name;

    private String key;

    private CommandType type;

    public RedisCommand(Object[] redisCommands) {
        this.redisCommands = redisCommands;
    }

    public Object[] getRedisCommands() {
        return redisCommands;
    }

    public void setRedisCommands(Object[] redisCommands) {
        this.redisCommands = redisCommands;
    }

    /***
     * 获得redis命令名称
     * 
     * @return
     */
    public byte[] getCmdName() {
        return getBytes(redisCommands[0]);
    }
    
    public byte[] getCmdKey(){
        return getBytes(redisCommands[1]);
    }

    private byte[] getBytes(Object object) {
        byte[] argument;
        if (object == null) {
            argument = EMPTY_BYTES;
        } else if (object instanceof byte[]) {
            argument = (byte[]) object;
        } else if (object instanceof ByteBuf) {
            argument = ((ByteBuf) object).array();
        } else if (object instanceof String) {
            argument = ((String) object).getBytes(Charsets.UTF_8);
        } else {
            argument = object.toString().getBytes(Charsets.UTF_8);
        }
        return argument;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pub.vrtech.transport.Decodeable#decode()
     */
    @Override
    public void decode() throws Exception {
        byte[] name = this.getCmdName();
        this.name = new String(BytesUtils.toLowerByte(name));
        this.type = CommandType.getCmdType(this.name);
        this.key =new String(BytesUtils.toLowerByte(this.getCmdKey()));
    }

    public CommandType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

}

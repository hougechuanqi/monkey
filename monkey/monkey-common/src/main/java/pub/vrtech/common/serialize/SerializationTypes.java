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
package pub.vrtech.common.serialize;

/**
 *
 * 序列化类型 Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public enum SerializationTypes {

    JSON((byte) 0, "JSON"),
    GOOGLE_PROTO((byte) 1, "google protobuffer"), 
    MESSAGE_PACK((byte) 2, "message pack"), 
    THRIFT((byte) 3, "thrift"), 
    NATIVE((byte) 4, "java native"), ;
    
    private final byte id;
    private final String desc;
    private SerializationTypes(final byte id, final String desc) {
        this.id = id;
        this.desc = desc;
    }
    public byte getId() {
        return id;
    }
    public String getDesc() {
        return desc;
    }
}

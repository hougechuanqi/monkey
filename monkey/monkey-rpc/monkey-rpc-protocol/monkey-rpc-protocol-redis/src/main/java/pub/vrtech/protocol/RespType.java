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

/**
 *
 * Function description： For Simple Strings the first byte of the reply is "+"
 * For Errors the first byte of the reply is "-" For Integers the first byte of
 * the reply is ":" For Bulk Strings the first byte of the reply is "$" For
 * Arrays the first byte of the reply is "*" refrence
 * http://redis.io/topics/protocol
 * 
 * @author houge
 */
public enum RespType {

    /***
     * 单行
     */
    STRING(0, (byte)'+'),
    /***
     * 错误消息
     */
    ERRORS(1, (byte)'-'),

    /***
     * 整形数字
     */
    INTEGERS(2, (byte)':'),

    /***
     * 批量回复
     */
    BULK_STRING(3,(byte)'$'),

    /***
     * 多个批量回复
     */
    ARRAY(4, (byte)'*'),

    ;

    private final int id;

    private final byte flag;

    private RespType(final int id, final byte flag) {
        this.id = id;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public byte getFlag() {
        return flag;
    }
 
}

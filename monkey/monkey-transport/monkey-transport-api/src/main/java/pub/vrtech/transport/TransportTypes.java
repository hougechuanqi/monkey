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

/**
 *
 * Function description：
 * 
 * 
 * @author houge
 */
public enum TransportTypes {

    /***
     * netty transport
     */
    NETTY(0, "netty"),

    ;

    private final int id;
    private final String desc;
    private TransportTypes(final int id, final String desc) {
        this.id = id;
        this.desc = desc;
    }
    public int getId() {
        return id;
    }
    public String getDesc() {
        return desc;
    }

}

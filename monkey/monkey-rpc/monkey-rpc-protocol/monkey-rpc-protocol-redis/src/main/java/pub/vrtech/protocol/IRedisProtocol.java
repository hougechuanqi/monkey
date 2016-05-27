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
 * Function description： 
 * 
 * @author houge
 */
public interface IRedisProtocol {

    /***
     * redis procotol terminated string
     */
    public final static String TERMINATED_STR = "\r\n";
    
    public static final byte CR = '\r';
    public static final byte LF = '\n';
    public  static final byte ZERO = '0';
    public static final byte ARRAY_START = '*';

    public static final byte ARGS_START = '$';
    
    

}

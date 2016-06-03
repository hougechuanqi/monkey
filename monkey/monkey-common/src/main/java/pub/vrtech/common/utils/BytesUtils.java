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
package pub.vrtech.common.utils;

import java.io.IOException;

/**
 *
 * Function description： 1.字节数组帮助类
 * 
 * @author houge
 */
public class BytesUtils {
    /****
     * 0 字节
     */
    public static final byte ZERO = '0';

    private static final byte LOWER_DIFF = 'a' - 'A';

    /***
     * byte数组转整形数字
     * 
     * @param bytesArray
     * @return
     * @throws IOException
     */
    public static int byteArray2Integer(final byte[] bytesArray)
            throws IOException {
        int destNumber = 0;
        for (byte b : bytesArray) {
            int value = b - ZERO;
            if (value >= 0 && value < 10) {
                destNumber *= 10;
                destNumber += value;
            } else {
                throw new IOException("Invalid character in integer");
            }
        }
        return destNumber;
    }

    /****
     * 大写转小写
     * @param source
     * @return
     */
    public static byte[] toLowerByte(byte[] source) {
        for (int i = 0; i < source.length; i++) {
            byte b = source[i];
            if (b >= 'A' && b <= 'Z') {
                source[i] = (byte) (b + LOWER_DIFF);
            }
        }
        return source;
    }

}

package org.vrtech.common.utils;

import org.vrtech.common.logs.Logger;
import org.vrtech.common.logs.LoggerFactory;

/***
 * 
 *
 * Function description：
 * 1.XXX
 * 2.XXX
 * @author houge
 */
public final class StringUtils {

    private static final Logger logger = LoggerFactory
            .getLogger(StringUtils.class);

    /**
     * join string.
     * 
     * @param array
     *            String array.
     * @return String.
     */
    public static String join(String[] array) {
        if (array.length == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (String s : array)
            sb.append(s);
        return sb.toString();
    }

    private StringUtils() {
    }
}
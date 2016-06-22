package pub.vrtech.common.utils;

import java.io.PrintWriter;

import pub.vrtech.common.io.UnsafeStringWriter;
import pub.vrtech.common.logs.Logger;
import pub.vrtech.common.logs.LoggerFactory;

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

    /**
     * 
     * @param e
     * @return string
     */
    public static String toString(Throwable e) {
        UnsafeStringWriter w = new UnsafeStringWriter();
        PrintWriter p = new PrintWriter(w);
        p.print(e.getClass().getName());
        if (e.getMessage() != null) {
            p.print(": " + e.getMessage());
        }
        p.println();
        try {
            e.printStackTrace(p);
            return w.toString();
        } finally {
            p.close();
        }
    }
    
    private StringUtils() {
    }
}
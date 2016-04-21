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
package org.vrtech.common.utils;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public class NetUtils {

    private static final Pattern LOCAL_IP_PATTERN = Pattern
            .compile("127(\\.\\d{1,3}){3}$");

    private static volatile InetAddress LOCAL_ADDRESS = null;
    public static final String LOCALHOST = "127.0.0.1";

    public static final String ANYHOST = "0.0.0.0";

    private static final Pattern IP_PATTERN = Pattern
            .compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");

    /***
     * 获得本机IP地址
     * 
     * @return
     */
    public static String getLogHost() {
        InetAddress address = LOCAL_ADDRESS;
        return address == null ? LOCALHOST : address.getHostAddress();
    }

    /***
     * 是否本地IP
     * 
     * @param host
     * @return
     */
    public static boolean isLocalHost(String host) {

        return host != null
                && (LOCAL_IP_PATTERN.matcher(host).matches() || host
                        .equalsIgnoreCase("localhost"));
    }

    /***
     * 是否任何主机
     * 
     * @param host
     * @return
     */
    public static boolean isAnyHost(String host) {
        return "0.0.0.0".equals(host);
    }

    /***
     * 是否是合法的本地IP
     * 
     * @param host
     * @return
     */
    public static boolean isValidLocalHost(String host) {
        return !isInvalidLocalHost(host);
    }

    /***
     * 是不合法的本地IP
     * 
     * @param host
     * @return
     */
    public static boolean isInvalidLocalHost(String host) {

        return host == null || host.length() == 0
                || host.equalsIgnoreCase("localhost") || host.equals("0.0.0.0")
                || (LOCAL_IP_PATTERN.matcher(host).matches());
    }

    private static boolean isValidAddress(InetAddress address) {
        if (address == null || address.isLoopbackAddress()) {
            return false;
        }
        String name = address.getHostAddress();
        return (name != null && !ANYHOST.equals(name)
                && !LOCALHOST.equals(name) && IP_PATTERN.matcher(name)
                .matches());
    }
    
    /**
     * @param hostName
     * @return ip address or hostName if UnknownHostException 
     */
    public static String getIpByHost(String hostName) {
        try{
            return InetAddress.getByName(hostName).getHostAddress();
        }catch (UnknownHostException e) {
            return hostName;
        }
    }
    
    public static String toAddressString(InetSocketAddress address) {
        return address.getAddress().getHostAddress() + ":" + address.getPort();
    }

}

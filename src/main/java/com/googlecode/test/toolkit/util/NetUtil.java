package com.googlecode.test.toolkit.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetUtil {

    private NetUtil() {
        // no instance;
    }

    public static String getLocalIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress().toString();
        } catch (UnknownHostException e) {
            throw new UtilException(e.getMessage(), e);
        }
    }

    public static String toIpFromDns(String dnsName) {
        try {
            return InetAddress.getByName(dnsName).getHostAddress();
        } catch (UnknownHostException e) {
            throw new UtilException(e.getMessage(), e);
        }
    }

}
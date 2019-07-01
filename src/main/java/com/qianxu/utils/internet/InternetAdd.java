package com.qianxu.utils.internet;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by 25131 on 2019/6/8.
 */
public class InternetAdd {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);
        System.out.println(localHost.getHostName());
        System.out.println(localHost.getHostAddress());
        InetAddress byName = InetAddress.getByName("www.baidu.com");
        System.out.println(byName);
        InetAddress[] allByName = InetAddress.getAllByName("www.taobao.com");
        for (InetAddress inetAddress : allByName) {
            System.out.println(inetAddress);
        }
    }
}

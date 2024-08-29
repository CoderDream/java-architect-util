package com.coderdream.demos.web.util;

import cn.hutool.core.net.NetUtil;

public class NetUtilTest {
    public static void main(String[] args) throws Exception{
        String host1 = "baidu.com";
        String host2 = "baiduu.com";
        int timeOut = 1000;//毫秒
        //当返回值是true时，说明host是可用的，false则不可。
        boolean ping1 = NetUtil.ping(host1, timeOut);
        boolean ping2 = NetUtil.ping(host2, timeOut);
        if (ping1){
            System.out.println(host1+"地址可用");
        }else {
            System.out.println(host1+"地址不可用");
        }

        if (ping2){
            System.out.println(host2+"地址可用");
        }else {
            System.out.println(host2+"地址不可用");
        }
    }
}

package com.gupaoedu.springcloud.example.demo01;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
public class MyPing implements IPing{

    @Override
    public boolean isAlive(Server server) {
        System.out.println("isAlive"+server.getHost()+":"+server.getPort());
        return true;
    }
}

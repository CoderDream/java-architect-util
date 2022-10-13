package com.gupaoedu.springcloud.example.demo01;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
public class GpDefineIpHashRule extends AbstractLoadBalancerRule{

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
    public Server choose(ILoadBalancer lb,Object key){
        if (lb == null) {
            return null;
        }
        Server server = null;
        while(server==null){
            List<Server> allList=lb.getAllServers();
            System.out.println(allList);
            int index=0;
            server=allList.get(index);
        }
        return server;
    }


    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(),key);
    }
}

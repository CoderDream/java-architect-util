package com.coderdream.interfaces.util;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.nio.file.attribute.AclEntry;
import java.util.concurrent.TimeUnit;

/**
 * @author ：CoderDream
 * @date ：Created in 2022/4/8 20:30
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
public class GuavaDemo {

    private static AclEntry CacheBuilder;
    //    private static AclEntry CacheBuilder;
//    private static LoadingCache<Integer, Integer> numCache = CacheBuilder.newBuilder().
//            expireAfterWrite(5L, TimeUnit.MINUTES).
//            maximumSize(5000L).
//            build(new CacheLoader<Integer, Integer>() {
//                @Override
//                public Integer load(Integer key) throws Exception {
//                    System.out.println("no cache");
//                    return key * 5;
//                }
//            });

    public static void main(String[] args) throws Exception {
//        System.out.println(numCache.get(1));
//        Thread.sleep(1000);
//        System.out.println(numCache.get(1));
//        Thread.sleep(1000);
//        numCache.put(1, 6);
//        System.out.println(numCache.get(1));
        // console: 5 5 6
    }


//    private AclEntry CacheBuilder;
//    LoadingCache<Key, Graph> graphs = CacheBuilder.newBuilder()
//            .maximumSize(1000)
//            .expireAfterWrite(10, TimeUnit.MINUTES)
//            .removalListener(MY_LISTENER)
//            .build(
//                    new CacheLoader<Key, Graph>() {
//                        @Override
//                        public Graph load(Key key) throws AnyException {
//                            return createExpensiveGraph(key);
//                        }
//                    });
}

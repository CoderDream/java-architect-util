package com.example.demo.self;

import com.example.demo.bean.Dept;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public class SelfCacheTest {
    /**
     * 测试软引用的内存敏感
     * -ea -Xms20M -Xmx20M -XX:+PrintGCDetails
     *
     * @throws InterruptedException
     */
    @Test
    public void test3() throws InterruptedException {
        Cache cache = new CacheVersionFinal(999);
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            System.out.println("放入第" + i + "个");
            Dept dept = new Dept((long) i);
            cache.put(dept.getId(), dept);
           // TimeUnit.SECONDS.sleep(1);
        }
    }

    /**
     * 测试强引用的oom
     * -ea -Xms20M -Xmx20M -XX:+PrintGCDetails
     */
    @Test
    public void test2() throws InterruptedException {
        Cache cache = new CacheVersion1(999999);
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            System.out.println("放入第" + i + "个");
            Dept dept = new Dept((long) i);
            cache.put(dept.getId(), dept);
          //  TimeUnit.SECONDS.sleep(1);
        }
    }

    @Test
    public void testCacheVersion2() {
        Cache cache = new CacheVersion2(3);
        cache.put("a", "a_value");
        cache.put("b", "b_value");
        cache.put("c", "c_value");
        // key:a,value:a_value-key:b,value:b_value-key:c,value:c_value-
        System.out.println(cache);
        // 访问一下演示，改变排序
        String bValue = (String) cache.get("b");
        System.out.println("b的值:" + bValue);
        // key:a,value:a_value-key:c,value:c_value-key:b,value:b_value-
        System.out.println(cache);
        /*
        测试是否会移除
         */
        cache.put("d", "d_value");
        // key:c,value:c_value-key:b,value:b_value-key:d,value:d_value-
        System.out.println(cache);
    }

    @Test
    public void testCacheVersion1() {
        Cache cache = new CacheVersion1(3);
        cache.put("a", "a_value");
        cache.put("b", "b_value");
        cache.put("c", "c_value");
        // {a=a_value, b=b_value, c=c_value}
        System.out.println(cache);
        // 访问一下演示，改变排序
        String bValue = (String) cache.get("b");
        System.out.println("b的值:" + bValue);
        // {a=a_value, c=c_value, b=b_value}
        System.out.println(cache);
        /*
        测试是否会移除
         */
        cache.put("d", "d_value");
        // CacheVersion1{capacity=3, internalCache={c=c_value, b=b_value, d=d_value}}
        System.out.println(cache);
    }

    /**
     * LRU:least recently used
     * 要实现基于LRU算法的溢出驱逐：
     * 1. 按访问时间来排序(a--b--c)
     * 2. 移除排在最前面(排序规则)的元素
     */
    @Test
    public void test1() {
        LinkedHashMap<String, String> linkedHashMap =
                new LinkedHashMap<>(16, 0.75f, true);
        linkedHashMap.put("a", "a_value");
        linkedHashMap.put("b", "b_value");
        linkedHashMap.put("c", "c_value");
        // {a=a_value, b=b_value, c=c_value}
        System.out.println(linkedHashMap);
        String bValue = linkedHashMap.get("b");
        System.out.println("b的值:" + bValue);
        // {a=a_value, c=c_value, b=b_value}
        System.out.println(linkedHashMap);
    }
}

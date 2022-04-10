package com.example.demo.ehcache;

import com.example.demo.bean.User;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */

public class EhcacheTest {

    @Test
    public void test2(){
        System.out.println(System.getProperty("java.io.tmpdir"));
        System.out.println(Class.class.getClass().getResource("/").getPath());
        System.out.println(System.getProperty("user.dir") );
    }
    /**
     * 单独使用ehcache的api进行编程
     */
    @Test
    public void test1(){
        String absPath = System.getProperty("user.dir")  + "\\src\\main\\resources\\ehcache\\ehcache.xml";
        /*
         用来管理多个Cache,user_cache、item_cache、store_cache...
         */
        CacheManager cacheManager = CacheManager.create(absPath);
        /*
         获取到CacheManager管理的所有的cache
         */
        String[] cacheNames = cacheManager.getCacheNames();
        System.out.println("CacheManager管理的所有的cache的名字"+ Arrays.toString(cacheNames));
        /*
         获取cache的名字(我们指定的)获取具体的cache
         */
        Cache userCache = cacheManager.getCache("user_cache");
        /*
         往userCache放入一个user
         */
        User user = new User();
        user.setId(1L);
        user.setName("乐之者java");
        Element element = new Element(user.getId(),user);
        userCache.put(element);
        /*
        通过key取出缓存的对象
         */
        Element resultEle = userCache.get(1L);
        System.out.println("获取到的resultEle:" +resultEle);
        System.out.println("获取Element的value:" + resultEle.getObjectValue());
    }
}

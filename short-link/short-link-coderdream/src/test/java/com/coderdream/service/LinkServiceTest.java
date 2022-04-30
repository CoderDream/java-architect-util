package com.coderdream.service;

import com.coderdream.helper.FileOperateHelper;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LinkServiceTest {
    @Resource
    private LinkService linkService;

    @Resource
    private FileOperateHelper fileOperateHelper;

    @Test
    public void testGetShortLink() {
        String shortLink = linkService.getShortLink("http://www.baidu.com/jLFYW7ZlML-344410");
        Assert.assertNotNull(shortLink);
        Assert.assertEquals("http://www.baidu.com/jLFYW7ZlML-344410", linkService.getLongLink(shortLink));
    }

    @Test
    public void testHashConflictLink() {
        String longLink1 = "http://www.baidu.com/8rkOPoKckoYowqylvIqMxG1V9HuUFJ-444728";
        String longLink2 = "http://www.baidu.com/twh2QwsOft5iard4aC8SPYvKulQy6C-921133";

        String shortLink1 = linkService.getShortLink(longLink1);
        String shortLink2 = linkService.getShortLink(longLink2);
        Assert.assertNotNull(shortLink1);
        Assert.assertEquals(longLink1, linkService.getLongLink(shortLink1));
        Assert.assertNotNull(shortLink2);
        Assert.assertEquals(longLink2, linkService.getLongLink(shortLink2));
    }

    @Test
    public void testStoreSameLink() {
        String longLink1 = "http://www.baidu.com/twh2QwsOft5iard4aC8SPYvKulQy6C-921133";
        String longLink2 = "http://www.baidu.com/twh2QwsOft5iard4aC8SPYvKulQy6C-921133";

        String shortLink1 = linkService.getShortLink(longLink1);
        String shortLink2 = linkService.getShortLink(longLink2);
        Assert.assertNotNull(shortLink1);
        Assert.assertEquals(longLink1, linkService.getLongLink(shortLink1));
        Assert.assertNotNull(shortLink2);
        Assert.assertEquals(longLink2, linkService.getLongLink(shortLink2));
        Assert.assertEquals(shortLink1, shortLink2);
        Assert.assertEquals(longLink1, longLink2);
    }


    /**
     * 预计要插入多少数据
     */
    private static int size = 1000000;

    /**
     * 期望的误判率
     */
    private static double fpp = 0.01;

    @Test
    public void testBloomFilterError() throws Exception {
        /**
         * 布隆过滤器
         */
        BloomFilter<String> filter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), size, fpp);

        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\data.txt";

        // BufferedReader是可以按行读取文件
        FileInputStream inputStream = new FileInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        String longLink = null;
        while ((longLink = bufferedReader.readLine()) != null) {
            //System.out.println("longLink: " + longLink);
            String code = linkService.getShortLink(longLink);
            //  list.add(code);
            set.add(code);
        }

        System.out.println("list size: " + list.size() + " ; set size: " + set.size());
        //close
        inputStream.close();
        bufferedReader.close();
        list.addAll(set);
        // 99948
        // int size1 = 190000;
        // int size2 = 10000;
        int size1 = 88948;
        int size2 = 10000;

        // 插入19万样本数据
        for (int i = 0; i < size1; i++) {
            filter.put(list.get(i));
        }
        // 用另外1万测试数据，测试误判率
        int count = 0;
        for (int i = size1; i < size1 + size2; i++) {
            if (filter.mightContain(list.get(i))) {
                count++;
                System.out.println(list.get(i) + "误判了");
            }
        }
        System.out.println("总共的误判数:" + count);
    }

}
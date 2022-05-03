package com.coderdream.helper;

import com.coderdream.utils.Constants;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BloomFilterHelperTest {
    @Resource
    private BloomFilterHelper bloomFilterHelper;

    @Test
    public void testCache() {
        assertFalse(bloomFilterHelper.mightContain("123"));
        bloomFilterHelper.put("123");
        assertTrue(bloomFilterHelper.mightContain("123"));
    }

    @Test
    public void testC() throws Exception {
        BloomFilter<String> filter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), Constants.BLOOM_FILTER_INSERTION, Constants.BLOOM_FILTER_FPP);

        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\data_06.txt";

        // BufferedReader是可以按行读取文件
        FileInputStream inputStream = new FileInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        String code = null;
        while ((code = bufferedReader.readLine()) != null) {
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
        int size1 = 430327;
        int size2 = 8;

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

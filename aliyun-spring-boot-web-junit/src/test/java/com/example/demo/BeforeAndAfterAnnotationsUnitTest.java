package com.example.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(JUnit4.class)
public class BeforeAndAfterAnnotationsUnitTest {

    // ...

    private List<String> list;

    @Before
    public void init() {
     //   LOG.info("startup");
        list = new ArrayList<>(Arrays.asList("test1", "test2"));
    }

    /**
     * 目标测试方法testTest()
     */
    @Test
    public void testTest() {
        System.out.println("public void testTest()");
        System.out.println(list);
       // flag = null;
    }

    @After
    public void teardown() {
     //   LOG.info("teardown");
        list.clear();
    }
}
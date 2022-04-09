package com.example.demo.util;

import com.example.demo.jacksondemo.util.EhCacheUtil;

import org.junit.Before;
import org.junit.Test;

/**
 * @author ：CoderDream
 * @date ：Created in 2022/4/9 23:52
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
public class EhCacheUtilTest {

    private EhCacheUtil ehCacheUtil;

    @Before
    public void setUp() {
        ehCacheUtil = new EhCacheUtil();
    }

    @Test
    public void testOperateUserCache() {
        ehCacheUtil.operateUserCache();
    }

}
package com.example.demo.util;

import com.example.demo.jacksondemo.util.Calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ：CoderDream
 * @date ：Created in 2022/4/10 0:08
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
public class CalculatorTest {

    private Calculator calculatorUnderTest;

    @Before
    public void setUp() {
        calculatorUnderTest = new Calculator();
    }

    @Test
    public void testAdd() {
        int result = calculatorUnderTest.add(1, 2);
        Assert.assertEquals(result, 3);
    }

    @Test
    public void testSub() {
        int result = calculatorUnderTest.sub(4, 2);
        Assert.assertEquals(result, 2);
    }

    @Test
    public void testMul() {
        int result = calculatorUnderTest.mul(1, 2);
        Assert.assertEquals(result, 2);
    }

    @Test
    public void testDev() {
        int result = calculatorUnderTest.dev(10, 2);
        Assert.assertEquals(result, 5);
    }
}

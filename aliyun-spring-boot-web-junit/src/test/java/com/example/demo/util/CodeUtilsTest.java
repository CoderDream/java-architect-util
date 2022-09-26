package com.example.demo.util;

import org.junit.Test;

public class CodeUtilsTest {

    @Test
    public void genStartCode_01() {
        String format = "MM";
        System.out.println(CodeUtils.genStartCode(format));
    }

    @Test
    public void genStartCode_02() {
        String format = "MMM";
        System.out.println(CodeUtils.genStartCode(format));
    }

    @Test
    public void genStartCode_03() {
        String format = "MMMM";
        System.out.println(CodeUtils.genStartCode(format));
    }

}
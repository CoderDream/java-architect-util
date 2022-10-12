package com.example.demo.util;

import org.junit.Assert;
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

    @Test
    public void genNextCode_0101() {
        Integer commonFlag = 1;
        String lastCode = "0Z";
        Result<String>  result = CodeUtils.genNextCode(lastCode, commonFlag);
        Assert.assertEquals("10", result.getData());
    }

    @Test
    public void genNextCode_0102() {
        Integer commonFlag = 1;
        String lastCode = "1Z";
        Result<String>  result = CodeUtils.genNextCode(lastCode, commonFlag);
        Assert.assertEquals("20", result.getData());
    }

    @Test
    public void genNextCode_0201() {
        Integer commonFlag = 0;
        String lastCode = "AZ";
        Result<String> result = CodeUtils.genNextCode(lastCode, commonFlag);
        System.out.println(result.getData());
        Assert.assertEquals("B0", result.getData());
    }

    @Test
    public void genNextCode_0202() {
        Integer commonFlag = 0;
        String lastCode = "0Z";
        Result<String>  result = CodeUtils.genNextCode(lastCode, commonFlag);
        System.out.println(result.getData());
        Assert.assertEquals("参数不合法：私有编码的第一位不是字母！", result.getMsg());
    }

}
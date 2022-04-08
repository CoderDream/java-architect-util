package com.duoji.shortlink.common;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;


/**
 * @author ：CoderDream
 * @date ：Created in 2022/4/7 22:41
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
public class Client {

    public static void getM128(String longLink){
        HashCode hashCode = Hashing.murmur3_128().hashString(longLink, StandardCharsets.UTF_8);
        System.out.println(hashCode.toString());
    }

    public static void getM32(String longLink){
        HashCode hashCode = Hashing.murmur3_32().hashString(longLink, StandardCharsets.UTF_8);
        System.out.println(hashCode.toString());
    }

    public static void main(String[] args) {
        String longLink = "https://www.cnblogs.com/strongmore/p/14493705.html";

        getM32(longLink);
        getM128(longLink);
    }

}

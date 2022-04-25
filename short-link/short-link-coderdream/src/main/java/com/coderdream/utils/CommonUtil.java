package com.coderdream.utils;

import com.google.common.hash.Hashing;

/**
 * @author ：CoderDream
 * @date ：Created in 2022/4/25 20:12
 * @description：
 * @modified By：CoderDream
 * @version: $
 */

/**
 * 功能描述
 *
 * @since 2022-04-22
 */
public class CommonUtil {
    /**
     * murmurhash算法
     *
     * @param param
     * @return
     */
    public static long murmurHash32(String param) {
        long murmurHash32 = Hashing.murmur3_32().hashUnencodedChars(param).padToLong();
        return murmurHash32;
    }
}
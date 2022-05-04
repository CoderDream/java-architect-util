package com.coderdream.helper;

import com.coderdream.utils.CommonUtil;
import org.springframework.stereotype.Component;

/**
 * @author ：CoderDream
 * @date ：Created in 2022/4/25 20:12
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
@Component
public class ShortLinkHelper {
    /**
     * 62个字符
     */
    private static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 生成短链码
     *
     * @param param
     * @return
     */
    public String createShortLinkCode(String param) {
        long murmurhash = CommonUtil.murmurHash32(param);
        //进制转换
        return encodeToBase62(murmurhash);
    }

    /**
     * 10进制转62进制
     *
     * @param num
     * @return
     */
    private String encodeToBase62(long num) {
        // StringBuffer线程安全，StringBuilder线程不安全
        StringBuffer sb = new StringBuffer();
        do {
            int i = (int) (num % 62);
            sb.append(CHARS.charAt(i));
            num = num / 62;
        } while (num > 0);
        return sb.reverse().toString();
    }
}

package com.example.demo.key;

import com.alibaba.fastjson.JSON;
import com.google.common.hash.Hashing;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

/**
 * 缓存的key,spel书写：
 * 1.有时候参数写的比较麻烦
 * 2.都要写key对应的表达式，可能忘记了
 * 3.我写了的话就以写的为准，不写就使用配置的KeyGenerator
 *
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public class MyKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder finalKey = new StringBuilder();
        // 方法全限定名
        finalKey.append(target.getClass().getName()).append(".")
                .append(method.getName()).append(".");
        if (params.length == 0) {
            return finalKey.append(0).toString();
        }
        for (Object param : params) {
            if (param == null) {
                finalKey.append("null");
            } else if (ClassUtils.isPrimitiveArray(param.getClass())) {
                // 一个参数为 int[] 等八种基本类型组成数组,不包含保证类
                int length = Array.getLength(param);
                for (int i = 0; i < length; i++) {
                    finalKey.append(Array.get(param, i)).append(",");
                }
            } else if (ClassUtils.isPrimitiveOrWrapper(param.getClass())
                    || param instanceof String) {
                // isPrimitiveOrWrapper: true:8中基本类型+void+8种基本类型的包装类型
                finalKey.append(param);
            } else {
                String paramStr = JSON.toJSONString(param);
                // 对字符串生成hash
                long murmurHash = Hashing.murmur3_128()
                        .hashString(paramStr, StandardCharsets.UTF_8)
                        .asLong();
                finalKey.append(murmurHash);
            }
            // 分隔多个参数
            finalKey.append("-");
        }
        System.out.println("最终的key:" + finalKey);
        return finalKey.toString();
    }
}

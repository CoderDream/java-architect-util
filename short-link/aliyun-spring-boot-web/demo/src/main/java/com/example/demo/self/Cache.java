package com.example.demo.self;

/**
 * 基本的缓存功能接口
 *
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface Cache {
    void put(Object key, Object value);

    void remove(Object key);

    void clear();

    Object get(Object key);

    int size();
}

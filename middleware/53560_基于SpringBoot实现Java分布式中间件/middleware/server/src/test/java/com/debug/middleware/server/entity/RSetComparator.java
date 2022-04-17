package com.debug.middleware.server.entity;

import java.util.Comparator;

/**
 * 集合RSet数据组件的自定排序
 * @author: zhonglinsen
 * @date: 2019/4/29
 */
public class RSetComparator implements Comparator<RSetDto>{
    /**
     * 自定义排序的逻辑
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(RSetDto o1, RSetDto o2) {
        return o2.getAge().compareTo(o1.getAge());
    }
}

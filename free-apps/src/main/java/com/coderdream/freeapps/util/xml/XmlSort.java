package com.coderdream.freeapps.util.xml;

import java.util.HashMap;
import java.util.LinkedHashMap;


/**
 * xml解析顺序是否有序
 * @author ASen
 */

public enum XmlSort{
    /**
     * 有序
     */
    SORT(LinkedHashMap.class,"有序"),
    /**
     * 无序
     */
    NO_SORT(HashMap.class,"无序");

    /**
     *  创建的map字节码对象
     */
    private final Class<?> mapClass;

    /**
     * 顺序名称
     */
    private final String message ;

    XmlSort(Class<?> mapClass, String message) {
        this.mapClass = mapClass;
        this.message = message;
    }

    public Class<?> getMapClass() {
        return mapClass;
    }

    public String getMessage() {
        return message;
    }
}

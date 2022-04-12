package com.coderdream.bean;

import lombok.Data;

/**
 * 链接对象
 */
@Data
public class Link {

    /**
     * 短链接
     */
    private String shortLink;

    /**
     * 长链接
     */
    private String longLink;

    /**
     * jvm回收对象
     */
    @Override
    protected void finalize() {
        System.out.println(shortLink + " 将要被回收");
    }
}

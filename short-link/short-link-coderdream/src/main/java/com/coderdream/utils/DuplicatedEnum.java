package com.coderdream.utils;

/**
 * @author ：CoderDream
 * @date ：Created in 2022/4/28 20:11
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
public enum  DuplicatedEnum {
    /**
     * DUPLICATED 重复
     */
    DUPLICATED("DUPLICATED");

    private String key;

    DuplicatedEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

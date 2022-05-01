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
     * 为了演示： 把key定义为D，实际过程中定义为： DUPLICATED
     */
    DUPLICATED("D"),

    /**
     * 把key定义为O，实际过程中定义为： OH_MY_GOD
     * oh_my_god
     */
    OH_MY_GOD("O");

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

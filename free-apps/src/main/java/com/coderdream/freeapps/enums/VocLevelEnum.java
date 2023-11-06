package com.coderdream.freeapps.enums;

/**
 *
 * 地区 枚举
 * @author CoderDream
 */
public enum VocLevelEnum {
    C01("C01", "初中"),
    C02("C02", "高中"),
    C03("C03", "四级"),
    C04("C04", "六级"),
    C05("C05", "考研"),
    C99("C99", "其他");

    private String label;
    private String name;

    private VocLevelEnum(String label, String name) {
        this.label = label;
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }

    public static VocLevelEnum match(String name) {
        for (VocLevelEnum item : VocLevelEnum.values()) {
            if (item.name.equals(name)) {
                return item;
            }
        }
        return null;
    }

    public static VocLevelEnum init(String label) {
        switch (label) {
            case "C01":
                return VocLevelEnum.C01;
            case "C02":
                return VocLevelEnum.C02;
            case "C03":
                return VocLevelEnum.C03;
            case "C04":
                return VocLevelEnum.C04;
            case "C05":
                return VocLevelEnum.C05;
            case "C99":
                return VocLevelEnum.C99;
            default:
                return null;
        }
    }

}

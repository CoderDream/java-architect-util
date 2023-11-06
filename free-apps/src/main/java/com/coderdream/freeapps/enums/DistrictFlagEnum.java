package com.coderdream.freeapps.enums;

/**
 *
 * 地区 枚举
 */
public enum DistrictFlagEnum {
    WU_HAN("wuhan", "武汉"),
    WU_CANG("wucang", "武昌"),
    NAN_HU("nanhu", "南湖"),
    HONG_SHAN("hongshan", "洪山"),
    GUANG_GU("guanggureal", "光谷"),

    GUANG_GU2("guanggu", "光谷周边");

    private String label;
    private String name;

    private DistrictFlagEnum(String label, String name) {
        this.label = label;
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }

    public static DistrictFlagEnum match(String name) {
        for (DistrictFlagEnum item : DistrictFlagEnum.values()) {
            if (item.name.equals(name)) {
                return item;
            }
        }
        return null;
    }

    public static DistrictFlagEnum init(String label) {
        switch (label) {
            case "wuhan":
                return DistrictFlagEnum.WU_HAN;
            case "wucang":
                return DistrictFlagEnum.WU_CANG;
            case "nanhu":
                return DistrictFlagEnum.NAN_HU;
            case "hongshan":
                return DistrictFlagEnum.HONG_SHAN;
            case "guanggu":
                return DistrictFlagEnum.GUANG_GU;
            default:
                return null;
        }
    }

}

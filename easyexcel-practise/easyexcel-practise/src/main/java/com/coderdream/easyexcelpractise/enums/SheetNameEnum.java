package com.coderdream.easyexcelpractise.enums;

/**
 *
 * Excel SheetName 枚举
 */
public enum SheetNameEnum {

    STANDARDIZE("YE_WU_DUI_XIANG", "规范"),
    BASIC("JI_CHU_SHU_XING", "基础属性"),
    DATA("SHU_JU_SHU_XING", "数据属性"),
    COMPOSE("ZU_CHENG_DUI_XIANG", "组成对象"),
    RELATION("GUAN_LIAN_DUI_XIANG", "关联对象"),
    BUSINESS("YE_WU_DUI_XIANG", "业务对象"),
    OBJECT_INSTANCE("OBJECT_INSTANCE", "对象实例"),
    ATTR_ITEM("ATTR_ITEM", "属性条目");

    private String label;
    private String name;

    private SheetNameEnum(String label, String name) {
        this.label = label;
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }

    public static SheetNameEnum match(String name) {
        for (SheetNameEnum item : SheetNameEnum.values()) {
            if (item.name.equals(name)) {
                return item;
            }
        }
        return null;
    }

    public static SheetNameEnum init(String value) {
        switch (value) {
            case "规范":
                return SheetNameEnum.STANDARDIZE;
            case "基础属性":
                return SheetNameEnum.BASIC;
            case "数据属性":
                return SheetNameEnum.DATA;
            case "组成对象":
                return SheetNameEnum.COMPOSE;
            case "关联对象":
                return SheetNameEnum.RELATION;
            case "业务对象":
                return SheetNameEnum.BUSINESS;
            case "对象实例":
                return SheetNameEnum.OBJECT_INSTANCE;
            case "属性条目":
                return SheetNameEnum.ATTR_ITEM;
            default:
                return null;
        }
    }

}

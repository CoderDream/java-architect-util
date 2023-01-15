package com.coderdream.demo.utils;

/**
 *
 * Excel SheetName 枚举
 */
public enum ImportSheetNameEnum {
    OBJECT_INSTANCE("instance", "对象实例"),
    ATTR_TYPE("attrType", "属性类型"),
    ATTR("attr", "属性"),
    ATTR_ITEM("attrItem", "属性条目"),
    STRING("string", "数值类数据"),
    CURVE("curve", "曲线类数据"),
    CURVE_META("curveMeta", "曲线元数据"),
    SEQUENCE("sequence", "时序数据"),
    FORECAST("forecast", "预报时序数据");

    private String label;
    private String name;

    private ImportSheetNameEnum(String label, String name) {
        this.label = label;
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }

    public static ImportSheetNameEnum match(String name) {
        for (ImportSheetNameEnum item : ImportSheetNameEnum.values()) {
            if (item.name.equals(name)) {
                return item;
            }
        }
        return null;
    }


    public static ImportSheetNameEnum init(String label) {
        switch (label) {
            case "instance":
                return ImportSheetNameEnum.OBJECT_INSTANCE;
            case "attrType":
                return ImportSheetNameEnum.ATTR_TYPE;
            case "attr":
                return ImportSheetNameEnum.ATTR;
            case "attrItem":
                return ImportSheetNameEnum.ATTR_ITEM;
            case "string":
                return ImportSheetNameEnum.STRING;
            case "curve":
                return ImportSheetNameEnum.CURVE;
            case "curveMeta":
                return ImportSheetNameEnum.CURVE_META;
            case "sequence":
                return ImportSheetNameEnum.SEQUENCE;
            case "forecast":
                return ImportSheetNameEnum.FORECAST;
            default:
                return null;
        }
    }

    public static ImportSheetNameEnum initWithName(String name) {
        switch (name) {
            case "对象实例":
                return ImportSheetNameEnum.OBJECT_INSTANCE;
            case "属性类型":
                return ImportSheetNameEnum.ATTR_TYPE;
            case "属性":
                return ImportSheetNameEnum.ATTR;
            case "属性条目":
                return ImportSheetNameEnum.ATTR_ITEM;
            case "数值类数据":
                return ImportSheetNameEnum.STRING;
            case "曲线类数据":
                return ImportSheetNameEnum.CURVE;
            case "曲线元数据":
                return ImportSheetNameEnum.CURVE_META;
            case "时序数据":
                return ImportSheetNameEnum.SEQUENCE;
            case "预报时序数据":
                return ImportSheetNameEnum.FORECAST;
            default:
                return null;
        }
    }

}

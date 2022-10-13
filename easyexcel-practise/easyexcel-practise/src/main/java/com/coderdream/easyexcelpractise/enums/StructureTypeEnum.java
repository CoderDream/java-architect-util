package com.coderdream.easyexcelpractise.enums;

/**
 * 基础属性	JI_CHU_SHU_XING	B
 * 数据属性	SHU_JU_SHU_XING	D
 * 组成对象	ZU_CHENG_DUI_XIANG	C
 * 关联对象	GUAN_LIAN_DUI_XIANG	R
 * 业务对象	YE_WU_DUI_XIANG	A
 *
 * 结构类型枚举
 */
public enum StructureTypeEnum {

    B("JI_CHU_SHU_XING", "基础属性"),
    D("SHU_JU_SHU_XING", "数据属性" ),
    C("ZU_CHENG_DUI_XIANG", "组成对象"),
    R("GUAN_LIAN_DUI_XIANG", "关联对象"),
    A("YE_WU_DUI_XIANG", "业务对象");

    private String label;
    private String name;

    private StructureTypeEnum(String label, String name  ) {
        this.label = label;
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }

    public static StructureTypeEnum match(String name){
        for (StructureTypeEnum item: StructureTypeEnum.values()) {
            if (item.name.equals(name)) {
                return item;
            }
        }
        return null;
    }

//    public static StructureTypeEnum init(String value) {
//        switch (value) {
//            case "string":
//                return StructureTypeEnum.STRING;
//            case "text":
//                return StructureTypeEnum.TEXT;
//            case "number":
//                return StructureTypeEnum.NUMBER;
//            case "json":
//                return StructureTypeEnum.JSON;
//            case "file":
//                return StructureTypeEnum.FILE;
//            case "geo":
//                return StructureTypeEnum.GEO;
//            case "curve":
//                return StructureTypeEnum.CURVE;
//            default:
//                return null;
//        }
//    }

}

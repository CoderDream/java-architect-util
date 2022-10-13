package com.coderdream.easyexcelpractise.enums;

/**
 * 数据类型枚举
 */
public enum DataTypeEnum {

    STRING("string", "短文本", "varchar"),
    TEXT("text", "长文本", "text"),
    NUMBER("number", "数字", "decimal"),
    JSON("json", "JSON", "json"),
    FILE("file", "文件", "blob"),
    GEO("geo", "地理信息", "geometry"),
    CURVE("curve", "曲线", "json");

    private String label;
    private String name;

    private String mySqlType;

    private DataTypeEnum(String label, String name, String mySqlType) {
        this.label = label;
        this.name = name;
        this.mySqlType = mySqlType;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }

    public String getMySqlType() {
        return mySqlType;
    }

    public static DataTypeEnum match(String label){
        for (DataTypeEnum item: DataTypeEnum.values()) {
            if (item.label.equals(label)) {
                return item;
            }
        }
        return null;
    }


    public static DataTypeEnum init(String value) {
        switch (value) {
            case "string":
                return DataTypeEnum.STRING;
            case "text":
                return DataTypeEnum.TEXT;
            case "number":
                return DataTypeEnum.NUMBER;
            case "json":
                return DataTypeEnum.JSON;
            case "file":
                return DataTypeEnum.FILE;
            case "geo":
                return DataTypeEnum.GEO;
            case "curve":
                return DataTypeEnum.CURVE;
            default:
                return null;
        }
    }

}

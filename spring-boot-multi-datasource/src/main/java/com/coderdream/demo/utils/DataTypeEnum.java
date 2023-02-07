package com.coderdream.demo.utils;

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
    POINT("point", "点状地理数据", "json"),
    LINE_STRING("linestring", "线状地理数据", "json"),
    POLYGON("polygon", "面状地理数据", "json"),
    CURVE("curve", "曲线", "json"),
    SEQUENCE("sequence", "时序数据", "json"),
    FORECAST("forecast", "预报时序数据", "json");

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
            case "point":
                return DataTypeEnum.POINT;
            case "linestring":
                return DataTypeEnum.LINE_STRING;
            case "polygon":
                return DataTypeEnum.POLYGON;
            case "curve":
                return DataTypeEnum.CURVE;
            case "sequence":
                return DataTypeEnum.SEQUENCE;
            case "forecast":
                return DataTypeEnum.FORECAST;
            default:
                return null;
        }
    }

    public static DataTypeEnum initByName(String name) {
        switch (name) {
            case "短文本":
                return DataTypeEnum.STRING;
            case "长文本":
                return DataTypeEnum.TEXT;
            case "数字":
                return DataTypeEnum.NUMBER;
            case "JSON":
                return DataTypeEnum.JSON;
            case "文件":
                return DataTypeEnum.FILE;
            case "地理信息":
                return DataTypeEnum.GEO;
            case "点状地理数据":
                return DataTypeEnum.POINT;
            case "线状地理数据":
                return DataTypeEnum.LINE_STRING;
            case "面状地理数据":
                return DataTypeEnum.POLYGON;
            case "曲线":
                return DataTypeEnum.CURVE;
            case "时序数据":
                return DataTypeEnum.SEQUENCE;
            case "预报时序数据":
                return DataTypeEnum.FORECAST;
            default:
                return null;
        }
    }

}

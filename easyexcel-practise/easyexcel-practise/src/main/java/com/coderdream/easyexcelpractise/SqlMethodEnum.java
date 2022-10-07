package com.coderdream.easyexcelpractise;

public enum SqlMethodEnum {

    BATCH_INSERT("insert", "insert"),BATCH_UPDATE("update", "update");

    private final String method;
    private final String sql;

    SqlMethodEnum(String method, String sql) {
        this.method = method;
        this.sql = sql;
    }

    public String getMethod() {
        return method;
    }

    public String getSql() {
        return sql;
    }
}

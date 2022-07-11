package com.example.demo.p03.dp22.strategy.bean.impl;

import com.example.demo.p03.dp22.strategy.bean.TableExporter;

/**
 * CSV格式，就是用逗号分格:
 */
public class TableExporterCSV extends TableExporter {
    public String getExported(String[][] data) {
        if (data == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (j > 0) {
                    sb.append(",");
                }
                sb.append(data[i][j]);
            }
            if (i < data.length - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}

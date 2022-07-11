package com.example.demo.p03.dp22.strategy.bean.impl;

import com.example.demo.p03.dp22.strategy.bean.TableExporter;

/**
 * HTML网页格式，输出时还要在每个表格单元,行前放空格：
 */
public class TableExporterHTML extends TableExporter {
    public String getExported(String[][] data) {
        if (data == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer("<table>\n");
        for (int i = 0; i < data.length; i++) {
            sb.append("  <tr>\n");
            for (int j = 0; j < data[i].length; j++) {
                sb.append("    <td>" + data[i][j] + "</td>\n");
            }
            sb.append("  </tr>\n");
        }
        return sb.append("</table>").toString();
    }
}

package com.example.demo.p03.dp22.strategy.bean;

import javax.swing.table.*;

public class DataModel extends AbstractTableModel {
    private String[][] mRows =
            {{"342", "543", "167"},
                    {"633", "207", "921"},
                    {"290", "544", "519"},
                    {"810", "105", "440"}};

    String[][] getAllData() {
        return mRows.clone();
    }

    public void setRows(String[][] pRows) {
        mRows = pRows;
    }

    public int getColumnCount() {
        if (mRows != null && mRows.length > 0 && mRows[0] != null) {
            return mRows[0].length;
        }
        return 0;
    }

    public int getRowCount() {
        if (mRows == null) {
            return 0;
        }
        return mRows.length;
    }

    public Object getValueAt(int row, int column) {
        return mRows[row][column];
    }

}

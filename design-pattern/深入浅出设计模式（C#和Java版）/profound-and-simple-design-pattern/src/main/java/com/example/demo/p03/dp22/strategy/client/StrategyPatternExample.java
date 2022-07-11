package com.example.demo.p03.dp22.strategy.client;

import com.example.demo.p03.dp22.strategy.bean.TableExporter;
import com.example.demo.p03.dp22.strategy.bean.impl.TableExporterCSV;
import com.example.demo.p03.dp22.strategy.bean.impl.TableExporterHTML;
import com.example.demo.p03.dp22.strategy.bean.impl.TableExporterTabs;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.HeadlessException;

public class StrategyPatternExample
        extends JFrame {
    private final JButton exportButton;
    private final JTable dataInput;
    private final JTextArea converterOutput;
    private final JRadioButton rbTabs;
    private final JRadioButton rbCSV;
    private final JRadioButton rbHTML;

    public StrategyPatternExample() throws HeadlessException {
        super("Strategy Pattern Example");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        JPanel listPanel = new JPanel();
        JPanel controlPanel = new JPanel();
        // data input
        dataInput = new JTable(new DataModel());
        JScrollPane dataInputPane = new JScrollPane(dataInput);
        dataInput.setPreferredScrollableViewportSize(new Dimension(200, 100));
        listPanel.add(dataInputPane);
        // converter output
        converterOutput = new JTextArea();
        converterOutput.setFont(new Font("monospaced", 0, 12));
        JScrollPane converterOutputPane = new JScrollPane(converterOutput);
        converterOutputPane.setPreferredSize(new Dimension(200, 120));
        listPanel.add(converterOutputPane);
        exportButton = new JButton("Export");
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doExport();
            }
        });
        ButtonGroup bg = new ButtonGroup();
        rbTabs = new JRadioButton("Tabs");
        rbCSV = new JRadioButton("CSV");
        rbHTML = new JRadioButton("HTML");
        bg.add(rbTabs);
        bg.add(rbCSV);
        bg.add(rbHTML);
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(rbTabs);
        controlPanel.add(rbCSV);
        controlPanel.add(rbHTML);
        controlPanel.add(exportButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(listPanel, BorderLayout.CENTER);
        getContentPane().add(controlPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) throws HeadlessException {
        StrategyPatternExample spe = new StrategyPatternExample();
        spe.show();
    }

    public void doExport() {
        TableExporter te = null;
        if (this.rbTabs.isSelected()) {
            te = new TableExporterTabs();
        } else if (rbCSV.isSelected()) {
            te = new TableExporterCSV();
        } else if (rbHTML.isSelected()) {
            te = new TableExporterHTML();
        }
        if (te != null) {
            converterOutput.setText(te.getExported(((DataModel) dataInput.
                    getModel()).getAllData()));
            converterOutput.select(0, 0);
        }
    }

    public class DataModel
            extends AbstractTableModel {
        private String[][] mRows = {
                {
                        "342", "543", "167"}
                , {
                "633", "207", "921"}
                , {
                "290", "544", "519"}
                , {
                "810", "105", "440"}
        };

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
}

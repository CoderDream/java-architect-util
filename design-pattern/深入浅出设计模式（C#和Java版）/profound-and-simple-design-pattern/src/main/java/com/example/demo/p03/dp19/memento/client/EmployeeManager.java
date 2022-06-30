package com.example.demo.p03.dp19.memento.client;

import com.example.demo.p03.dp19.memento.bean.Employee;
import com.example.demo.p03.dp19.memento.bean.EmployeeMemento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *
 */
public class EmployeeManager {
    private final JButton loadButton = new JButton("Load");
    private final JButton saveButton = new JButton("Save");
    private final JButton undoSaveButton = new JButton("Undo Save");
    private final JTextField idText = new JTextField();
    private final JTextField firstNameText = new JTextField();
    private final JTextField lastNameText = new JTextField();
    private final JTextField salaryText = new JTextField();
    private final JFrame frame;
    //caretaker对象，这里是一个Vector对象
    private final Vector caretaker;
    //这就是我们需要处理的employee(雇员)对象
    private final Employee employee = new Employee();


    EmployeeManager() {
        frame = new JFrame("Memento Pattern Example");
        frame.setLocation(200, 200);
        caretaker = new Vector();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // create input field panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(4, 2));
        topPanel.add(new JLabel("ID:"));
        topPanel.add(idText);
        topPanel.add(new JLabel("First Name:"));
        topPanel.add(firstNameText);
        topPanel.add(new JLabel("Last Name:"));
        topPanel.add(lastNameText);
        topPanel.add(new JLabel("Salary:"));
        topPanel.add(salaryText);

        // 生成按钮面板
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(loadButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(undoSaveButton);
        //设置每个按钮的动作处理
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadEmployee();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveEmployee();
            }
        });
        undoSaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                undoEmployee();
            }
        });
        //将按钮面板增加入框架中，及显示出来
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(topPanel, BorderLayout.CENTER);
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new EmployeeManager();

    }

    //caretaker保存Employee的状态
    private void saveEmployee() {
        EmployeeMemento memento;
        employee.setId(Integer.parseInt(idText.getText()));
        employee.setFirstName(firstNameText.getText());
        employee.setLastName(lastNameText.getText());
        employee.setSalary(Integer.parseInt(salaryText.getText()));
        memento = employee.getMemento();
        caretaker.addElement(memento);
        idText.setText("");
        firstNameText.setText("");
        lastNameText.setText("");
        salaryText.setText("");
    }

    //取消最近保存雇员状态,并且显示其取消的状态
    private void undoEmployee() {
        EmployeeMemento memento;
        try {
            memento = (EmployeeMemento) caretaker.lastElement();
            employee.setMemento(memento);
            caretaker.removeElement(memento);
            loadEmployee();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void loadEmployee() {
        idText.setText("" + employee.getId());
        firstNameText.setText(employee.getFirstName());
        lastNameText.setText(employee.getLastName());
        salaryText.setText("" + employee.getSalary());
    }
}

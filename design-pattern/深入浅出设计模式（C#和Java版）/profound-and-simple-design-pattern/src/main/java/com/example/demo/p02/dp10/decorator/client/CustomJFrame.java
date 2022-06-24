package com.example.demo.p02.dp10.decorator.client;

import com.example.demo.p02.dp10.decorator.bean.CustomJButton;

import javax.swing.*;
import java.awt.*;

public class CustomJFrame extends JFrame {
    public CustomJFrame() {
        setSize(400, 200);
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(new CustomJButton(new JButton("Save")));
        show();
    }

    public static void main(String[] args) {
        new CustomJFrame();
    }
}

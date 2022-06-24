package com.example.demo.p02.dp10.decorator.bean;


import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomJButton extends JComponent {
    // decorated component
    protected JComponent child;

    public CustomJButton(JComponent component) {
        child = component;
        this.setLayout(new BorderLayout());
        this.add(child);
        component.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                repaint();
            }
        });
        component.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent fe) {
                repaint();
            }

            public void focusLost(FocusEvent fe) {
                repaint();
            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);
        int height = this.getHeight();
        int width = this.getWidth();
        g.setColor(Color.red);
        g.setFont(new Font("", Font.BOLD, 2));
        g.drawLine(0, 0, width, height);
    }
}

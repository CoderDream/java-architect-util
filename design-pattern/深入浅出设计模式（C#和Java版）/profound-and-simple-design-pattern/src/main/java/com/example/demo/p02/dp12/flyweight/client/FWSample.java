package com.example.demo.p02.dp12.flyweight.client;

import com.example.demo.p02.dp12.flyweight.bean.MyImage;
import com.example.demo.p02.dp12.flyweight.bean.MyImageFactory;

import javax.swing.*;
import java.awt.*;

/**
 * 客户应用,动态绘画不同的图象在不同的位置
 */
public class FWSample extends JFrame {
    public MyImageFactory factory;

    public FWSample() {
        super("flyweight sample");
        factory = new MyImageFactory();
        setSize(220, 300);
        setVisible(true);
        repaint();
    }

    public static void main(String[] argv) {
        new FWSample();
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        String name;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int number = (int) ((Math.random() * 3) % 3);
                if (number == 0)
                    name = "love";
                else if (number == 1)
                    name = "pig";
                else
                    name = "bird";
                MyImage myImage = factory.getMyImage(name);
                myImage.draw(g, 10 + i * 40, 45 + j * 45, name, this);
            }
        }
    }
}

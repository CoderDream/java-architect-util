package com.example.demo.p02.dp12.flyweight.bean;
//定义MyImage类,包括Image属性,绘画方法：

import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class MyImage {
    Image image;

    public MyImage(String file) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        // System.out.println(System.getProperty("user.dir"));
        String path = System.getProperty("user.dir");
        file = path + "\\src\\main\\java\\com\\example\\demo\\p02\\dp12\\flyweight\\bean\\" + file;
        File f = new File(file);
        if (f.exists() && f.isFile() && f.canRead()) {
            image = toolkit.getImage(file);
        } else System.err.println("Unable to load file " + file);
    }

    public void draw(Graphics g, int x, int y, String name, ImageObserver obs) {
        g.drawImage(image, x, y, 30, 30, obs);
        g.drawString(name, x, y + 40);
    }
}


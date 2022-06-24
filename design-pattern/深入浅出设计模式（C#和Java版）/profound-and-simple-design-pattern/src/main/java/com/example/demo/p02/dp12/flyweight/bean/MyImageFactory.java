package com.example.demo.p02.dp12.flyweight.bean;

/**
 * image工厂类,包括初始化image，返回内存中的image方法
 */
public class MyImageFactory {
    MyImage image1, image2, image3;

    public MyImageFactory() {
        image1 = new MyImage("love.GIF");
        image2 = new MyImage("pig.gif");
        image3 = new MyImage("bird.gif");
    }

    public MyImage getMyImage(String name) {
        if (name.equals("love")) {
            return image1;
        } else if (name.equals("pig")) {
            return image2;
        } else if (name.equals("bird")) {
            return image3;
        }
        return null;
    }
}

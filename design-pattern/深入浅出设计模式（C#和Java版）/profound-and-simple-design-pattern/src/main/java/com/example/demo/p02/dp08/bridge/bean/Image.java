package com.example.demo.p02.dp08.bridge.bean;

/**
 * 抽象Image接口，具有ImageImp的引用。
 */
public abstract class Image {
    protected ImageInterface imageInterface;

    public void setImageInterface(ImageInterface imageInterface) {
        this.imageInterface = imageInterface;
    }

    public abstract void method(String str);
}

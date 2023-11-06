package cn.xupengzhuang.chapter09.example01;

public interface Resizable extends Drawable{
    int getWidth();
    int getHeight();

    void setWidth();
    void setHeight();

    void setAbsoluteSize(int width,int height);

    //新版本添加了一个新方法
    void setRelativeSize(int wFactor, int hFactor);
}

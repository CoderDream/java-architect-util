package cn.xupengzhuang.chapter09.example01;

public class Ellipse implements Resizable{
    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void setWidth() {

    }

    @Override
    public void setHeight() {

    }

    @Override
    public void setAbsoluteSize(int width, int height) {

    }

    /**
     * 当接口中添加了一个新方法后，所有实现了该接口的类都必须添加该方法的实现
     * @param wFactor
     * @param hFactor
     */
    @Override
    public void setRelativeSize(int wFactor, int hFactor) {

    }

    @Override
    public void draw() {

    }
}

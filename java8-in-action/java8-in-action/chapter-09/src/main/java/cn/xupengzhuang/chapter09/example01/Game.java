package cn.xupengzhuang.chapter09.example01;

import java.util.Arrays;
import java.util.List;

public class Game {
    public static void main(String[] args) {
        List<Resizable> list = Arrays.asList(new Ellipse());
        Utils.paint(list);
    }
}

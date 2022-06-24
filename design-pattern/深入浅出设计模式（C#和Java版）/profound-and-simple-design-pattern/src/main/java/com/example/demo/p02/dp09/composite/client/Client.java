package com.example.demo.p02.dp09.composite.client;

import com.example.demo.p02.dp09.composite.bean.impl.CustomCharacter;
import com.example.demo.p02.dp09.composite.bean.impl.CustomColumn;
import com.example.demo.p02.dp09.composite.bean.impl.Image;
import com.example.demo.p02.dp09.composite.bean.impl.Page;

/**
 * 顾客应用测试
 */
public class Client {
    public static void main(String[] args) {
        Page page = new Page();
        CustomCharacter c1 = new CustomCharacter(1, 1);
        Image img1 = new Image(2, 2);
        CustomColumn col = new CustomColumn();
        CustomCharacter c2 = new CustomCharacter(1, 3);
        Image img2 = new Image(2, 3);
        col.add(c2);
        col.add(img2);
        page.add(c1);
        page.add(img1);
        page.add(col);
        page.draw();
    }
}

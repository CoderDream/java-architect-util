package com.example.demo.p02.dp09.composite.bean.impl;

import com.example.demo.p02.dp09.composite.bean.CompositeDocumentElement;

/**
 * 定义Column类，由CompositeDocumentElement派生出来：
 */
public class CustomColumn extends CompositeDocumentElement {
    public void draw() {
        System.out.println("Column content:");
        super.draw();
    }

}

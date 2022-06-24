package com.example.demo.p02.dp09.composite.bean.impl;

import com.example.demo.p02.dp09.composite.bean.CompositeDocumentElement;

/**
 * 定义CompositeDocumentElement的子类
 */
public class Page extends CompositeDocumentElement {
    public void draw() {
        System.out.println("Page content:");
        super.draw();
    }
}

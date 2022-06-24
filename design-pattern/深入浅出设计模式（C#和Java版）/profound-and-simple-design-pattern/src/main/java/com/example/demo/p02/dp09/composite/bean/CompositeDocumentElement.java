package com.example.demo.p02.dp09.composite.bean;

import java.util.Vector;

/**
 * 定义抽象的CompositeDocumentElement类，实现DocumentElement接口
 */
public abstract class CompositeDocumentElement implements DocumentElement {
    private final Vector vector = new Vector();

    public void draw() {
        for (int i = 0; i < vector.size(); i++) {
            ((DocumentElement) vector.elementAt(i)).draw();
        }
    }

    public void add(DocumentElement doc) {
        vector.add(doc);
    }
}

package com.example.demo.p01.dp05.prototype.bean.impl;

import com.example.demo.p01.dp05.prototype.bean.Command;
import com.example.demo.p01.dp05.prototype.bean.ToolPrototype;

public class Rectangle implements ToolPrototype, Command {
    @Override
    public void draw() {
        System.out.println("Draw a rectangle");
    }

    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException exception) {
            System.err.println("Clone not support");
        }
        return clone;
    }

    @Override
    public String getName() {
        return "Rectangle";
    }

}

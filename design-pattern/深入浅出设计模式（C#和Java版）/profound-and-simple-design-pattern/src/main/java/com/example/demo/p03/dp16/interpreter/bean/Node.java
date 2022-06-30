package com.example.demo.p03.dp16.interpreter.bean;

import com.example.demo.p03.dp16.interpreter.bean.impl.Context;

public interface Node {
    void parse(Context context);

    void execute();
}

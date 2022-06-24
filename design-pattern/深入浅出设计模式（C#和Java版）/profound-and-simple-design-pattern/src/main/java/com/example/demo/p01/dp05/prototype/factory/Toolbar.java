package com.example.demo.p01.dp05.prototype.factory;

import com.example.demo.p01.dp05.prototype.bean.impl.Circle;
import com.example.demo.p01.dp05.prototype.bean.impl.Rectangle;

import java.util.HashMap;


public class Toolbar {
    public HashMap objPool;

    public Toolbar() {
        objPool = new HashMap();
        this.Register("circle", new Circle());
        this.Register("rectangle", new Rectangle());
    }

    public void Register(Object key, Object object) {
        objPool.put(key, object);
    }

    public Object getClone(String key) {
        return objPool.get(key);
    }
}

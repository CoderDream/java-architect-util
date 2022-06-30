package com.example.demo.p03.dp17.iterator.bean;


import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

public class IntSet {
    private final Hashtable ht = new Hashtable();

    public void add(int in) {
        ht.put(new Integer(in), "null");
    }

    public boolean isMember(int i) {
        return ht.containsKey(new Integer(i));
    }

    public Hashtable getHashtable() {
        return ht;
    }

    /**
     * 在聚集类的内部包括生成迭代器createIterator的方法
     *
     * @return
     */
    public Iterator createIterator() {
        return new Iterator(this);
    }

    /**
     * 设计一个内部的迭代器类操纵聚集类
     */
    public static class Iterator {
        private final IntSet set;
        private Enumeration e;
        private Integer current;

        public Iterator(IntSet in) {
            set = in;
        }

        public void first() {
            e = set.ht.keys();
            next();
        }

        public boolean isDone() {
            return current == null;
        }

        public int currentItem() {
            return current.intValue();
        }

        public void next() {
            try {
                current = (Integer) e.nextElement();
            } catch (NoSuchElementException e) {
                current = null;
            }
        }
    }
}

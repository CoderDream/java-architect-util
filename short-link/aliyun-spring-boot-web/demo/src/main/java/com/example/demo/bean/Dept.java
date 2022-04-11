package com.example.demo.bean;

import lombok.Data;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Data
public class Dept {
    private Long id;
    /**
     * 1M
     */
    private byte[] bytes = new byte[1024 * 1024];

    public Dept(Long id) {
        this.id = id;
    }

    /**
     * jvm要回收你这个对象了，会回调这个方法，你可以在这个方法里面完成资源的清理
     * 或者完成自救
     *
     * @throws Throwable 异常
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println(id + "将要被回收,赶紧想法自救吧....");
    }
}

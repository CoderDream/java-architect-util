package com.debug.middleware.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * Redisson的集合数据组件RSet实体信息
 * @author: zhonglinsen
 * @date: 2019/4/29
 */
@Data
@ToString
@EqualsAndHashCode
public class RSetDto implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private Double score;

    public RSetDto() {
    }

    public RSetDto(Integer id, String name, Double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public RSetDto(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}

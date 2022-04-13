package com.debug.middleware.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.io.Serializable;

/**
 * 映射数据结构RMap的实体信息
 * @author: zhonglinsen
 * @date: 2019/4/29
 */
@Data
@ToString
@EqualsAndHashCode
public class RMapDto implements Serializable {
    private Integer id;  //id
    private String name; //名称

    public RMapDto() {
    }

    public RMapDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}

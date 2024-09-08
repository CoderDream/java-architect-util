package com.coderdream.middleware.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import lombok.Data;

/**
 * @author CoderDream
 */
@Data
public class Item {

    /**
     * 书籍ID
     */
    private Integer id;

    /**
     * 书籍编码
     */
    private String code;

    /**
     * 书籍名称
     */
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}

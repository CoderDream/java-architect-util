package com.coderdream.freeapps.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName t_blog
 */
@TableName(value ="t_blog")
@Data
public class BlogEntity implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private Long categoryId;

    /**
     *
     */
    private String appId;

    /**
     *
     */
    private String title;
    /**
     *
     */
    private String content;

    /**
     *
     */
    private Date createTime;

}

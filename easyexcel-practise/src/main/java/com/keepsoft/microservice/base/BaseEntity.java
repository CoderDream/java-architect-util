package com.keepsoft.microservice.base;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础@Entity(还需在XML文件里，有对应的SQL语句)
 *
 * @author wangwenbing
 * @email wangwenbing@163.com
 * @date 2017年11月4日 上午11:07:35
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -6259312242789016371L;

    @TableField(exist = false)
    private Integer current;

    @TableField(exist = false)
    private Integer total;

    @TableField(exist = false)
    private Integer size;

}

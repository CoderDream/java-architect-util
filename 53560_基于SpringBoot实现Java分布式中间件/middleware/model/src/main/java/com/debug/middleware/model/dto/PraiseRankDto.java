package com.debug.middleware.model.dto;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

/**
 * 博客点赞总数排行
 * @Author:debug (SteadyJack)
 * @Date: 2019/5/4 14:29
 **/
@Data
@ToString
public class PraiseRankDto implements Serializable{
    private Integer blogId; //博客id
    private Long total;     //点赞总数

    //空的构造器
    public PraiseRankDto() {
    }
    //包含所有字段的构造器
    public PraiseRankDto(Integer blogId, Long total) {
        this.blogId = blogId;
        this.total = total;
    }
}

















package com.debug.middleware.server.dto;

import lombok.Data;
import lombok.ToString;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 接受前端用户点赞博客的信息的实体对象
 * @author: zhonglinsen
 */
@Data
@ToString
public class PraiseDto implements Serializable {
    @NotNull
    private Integer blogId;     //博客id-必填
    @NotNull
    private Integer userId;     //点赞/取消点赞的用户id-必填
}










































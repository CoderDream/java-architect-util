package com.debug.middleware.model.mapper;

import com.debug.middleware.model.dto.PraiseRankDto;
import com.debug.middleware.model.entity.Praise;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 点赞实体操作接口Mapper
 */
public interface PraiseMapper {
    //插入点赞信息
    int insertSelective(Praise record);

    //根据博客id跟用户id查询点赞记录
    Praise selectByBlogUserId(@Param("blogId") Integer blogId, @Param("uId") Integer uId);
    //根据博客id查询总的点赞数
    int countByBlogId(@Param("blogId") Integer blogId);

    //取消点赞博客
    int cancelPraiseBlog(@Param("blogId") Integer blogId, @Param("uId") Integer uId);
    //获取博客点赞总数排行榜
    List<PraiseRankDto> getPraiseRank();
}
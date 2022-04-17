package com.debug.middleware.server.service;

import com.debug.middleware.server.dto.PraiseDto;
import com.debug.middleware.model.dto.PraiseRankDto;
import java.util.Collection;

/**
 * 点赞业务处理接口
 * @author: zhonglinsen
 */
public interface IPraiseService {
    //点赞博客-无锁
    void addPraise(PraiseDto dto) throws Exception;
    //点赞博客-加分布式锁
    void addPraiseLock(PraiseDto dto) throws Exception;

    //取消点赞博客
    void cancelPraise(PraiseDto dto) throws Exception;

    //获取博客的点赞数
    Long getBlogPraiseTotal(Integer blogId) throws Exception;

    //获取博客点赞总数排行榜-不采用缓存
    Collection<PraiseRankDto> getRankNoRedisson() throws Exception;
    //获取博客点赞总数排行榜-采用缓存
    Collection<PraiseRankDto> getRankWithRedisson() throws Exception;
}

package com.coderdream.controller.impl;

import com.coderdream.common.Result;
import com.coderdream.common.ResultBuilder;
import com.coderdream.controller.LinkController;
import com.coderdream.service.LinkService;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author ：CoderDream
 * @date ：Created in 2022/4/6 23:18
 * @description：链接控制器
 * @modified By：CoderDream
 * @version: $
 */
@Controller
public class LinkControllerImpl implements LinkController {
//    @Resource
//    private CacheManager cacheManager;

    @Resource
    private LinkService linkService;

    @Override
    public Result<String> getShortLink(String longLink) {
        String shortLink = linkService.getShortLink(longLink);

        return ResultBuilder.buildSuccess(shortLink);
    }

    @Override
    public Result<String> getLongLink(String shortLink) {
        String longLink = linkService.getLongLink(shortLink);

        return ResultBuilder.buildSuccess(longLink);
    }
}

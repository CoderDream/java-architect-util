package com.coderdream.controller.impl;

import com.coderdream.common.Result;
import com.coderdream.common.ResultBuilder;
import com.coderdream.controller.LinkController;
import com.coderdream.service.LinkService;
import org.apache.commons.lang3.StringUtils;
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
    @Resource
    private LinkService linkService;

    @Override
    public Result<String> getShortLink(String longLink) {
        if (StringUtils.isEmpty(longLink)) {
            throw new IllegalArgumentException("longLink不可为空");
        }
        String shortLink = linkService.getShortLink(longLink);

        return ResultBuilder.buildSuccess(shortLink);
    }

    @Override
    public Result<String> getLongLink(String shortLink) {
        String longLink = linkService.getLongLink(shortLink);

        return ResultBuilder.buildSuccess(longLink);
    }
}

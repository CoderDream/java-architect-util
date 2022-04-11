package com.coderdream.interfaces.controller.impl;

import com.coderdream.common.Result;
import com.coderdream.common.ResultBuilder;
import com.coderdream.interfaces.controller.LinkController;
import org.springframework.stereotype.Controller;

import javax.xml.ws.Response;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author ：CoderDream
 * @date ：Created in 2022/4/6 23:18
 * @description：链接控制器
 * @modified By：CoderDream
 * @version: $
 */
@Controller
public class LinkControllerImpl implements LinkController {

    @Override
    public Result<String> getShortLink(String longLink) {
        String shortLink = "Short Link: " + longLink;

        return ResultBuilder.buildSuccess(shortLink);
    }

    @Override
    public Result<String> getLongLink(String shortLink) {
        String longLink = "Long Link: " + shortLink;

        return ResultBuilder.buildSuccess(longLink);
    }
}

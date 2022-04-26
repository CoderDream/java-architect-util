package com.coderdream.service.impl;

import com.coderdream.bean.ShortLinkBean;
import com.coderdream.helper.GuavaCacheHelper;
import com.coderdream.service.LinkService;
import com.coderdream.utils.Config;
import com.coderdream.utils.ShortLinkComponent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LinkServiceImpl implements LinkService {
    @Resource
    private Config config;

    @Resource
    private ShortLinkComponent shortLinkComponent;

    @Resource
    private GuavaCacheHelper guavaCacheHelper;

    @Override
    public String getShortLink(String longLink) {
        String code = shortLinkComponent.createShortLinkCode(longLink);

        ShortLinkBean shortLinkBean = new ShortLinkBean();
        shortLinkBean.setShortLink(code);
        shortLinkBean.setLongLink(longLink);
        shortLinkBean.setExpireTime(System.currentTimeMillis() + config.EXPIRE_SEC * 1000);
        guavaCacheHelper.put(code, shortLinkBean);

        return code;
    }

    @Override
    public String getLongLink(String shortLink) {
        Object value = guavaCacheHelper.get(shortLink);
        if (value == null) {
            return null;
        }
        return ((ShortLinkBean) value).getLongLink();
    }
}

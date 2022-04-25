package com.coderdream.service.impl;

import com.coderdream.service.LinkService;
import com.coderdream.utils.ShortLinkComponent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LinkServiceImpl implements LinkService {

    @Resource
    private ShortLinkComponent shortLinkComponent;

    @Override
    public String getShortLink(String longLink) {
        return shortLinkComponent.createShortLinkCode(longLink);
    }

    @Override
    public String getLongLink(String shortLink) {
        return null;
    }
}

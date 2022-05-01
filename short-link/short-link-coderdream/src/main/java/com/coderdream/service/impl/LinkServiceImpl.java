package com.coderdream.service.impl;

import com.coderdream.bean.ShortLinkBean;
import com.coderdream.helper.BloomFilterHelper;
import com.coderdream.helper.GuavaCacheHelper;
import com.coderdream.service.LinkService;
import com.coderdream.utils.Config;
import com.coderdream.utils.DuplicatedEnum;
import com.coderdream.utils.ShortLinkComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class LinkServiceImpl implements LinkService {
    @Resource
    private Config config;

    @Resource
    private ShortLinkComponent shortLinkComponent;

    @Resource
    private GuavaCacheHelper guavaCacheHelper;

    @Resource
    private BloomFilterHelper bloomFilterHelper;

    @Override
    public String getShortLink(String longLink) {
        String code = shortLinkComponent.createShortLinkCode(longLink);

        ShortLinkBean shortLinkBean = new ShortLinkBean();
        shortLinkBean.setShortLink(code);
        shortLinkBean.setLongLink(longLink);
        shortLinkBean.setExpireTime(System.currentTimeMillis() + config.EXPIRE_SEC * 1000);
        // 通过布隆过滤器判断：如果不存在（100%正确），则直接放入缓存中
        if (!bloomFilterHelper.mightContain(code)) {
            guavaCacheHelper.put(code, shortLinkBean);
            // 把短链接放入布隆过滤器
            bloomFilterHelper.put(code);
        }
        // 如果存在（可能误判）
        else {
            // 从缓存中取对象
            ShortLinkBean oldShortLinkBean = (ShortLinkBean) guavaCacheHelper.get(code);
            // 如果不存在误判为存在，则直接将新的数据写入缓存中
            if (oldShortLinkBean == null) {
                guavaCacheHelper.put(code, shortLinkBean);
                // 把短链接放入布隆过滤器
                bloomFilterHelper.put(code);
                log.error("布隆过滤器误判： new code: " + code + "; old link: " + oldShortLinkBean.getLongLink()
                        + " ; new link: " + longLink);
            }
            // 如果确实存在
            else {
                String oldLongLink = oldShortLinkBean.getLongLink();
                // 判断是否Hash冲突了(code相同，长链接url不同)
                if (code.equals(oldShortLinkBean.getShortLink()) && !longLink.equals(oldLongLink)) {
                    // 记录日志
                    log.warn("Hash冲突, old and new code: " + code + "; old link: " + oldLongLink + " ; new link: "
                            + longLink);

                    String newLongLink = "";
                    // 第一轮新code、新link
                    if (!oldLongLink.startsWith(DuplicatedEnum.DUPLICATED.getKey()) && !oldLongLink.startsWith(
                            DuplicatedEnum.OH_MY_GOD.getKey())) {
                        if (!oldLongLink.startsWith(DuplicatedEnum.DUPLICATED.getKey())) {
                            // code加上枚举前缀后Hash
                            code = shortLinkComponent.createShortLinkCode(DuplicatedEnum.DUPLICATED.getKey() + "_" + code);
                            newLongLink = DuplicatedEnum.DUPLICATED.getKey() + "_" + longLink;
                            log.error("Hash第一次冲突解决： new code: " + code + "; old link: " + oldShortLinkBean.getLongLink()
                                    + " ; new link: " + newLongLink);
                        } else {
                            code = shortLinkComponent.createShortLinkCode(
                                    DuplicatedEnum.OH_MY_GOD.getKey() + "_" + code);
                            newLongLink = DuplicatedEnum.OH_MY_GOD.getKey() + "_" + longLink;
                            log.error("Hash第二次冲突解决： new code: " + code + "; old link: " + oldShortLinkBean.getLongLink()
                                    + " ; new link: " + newLongLink);
                        }
                    }

                    shortLinkBean.setShortLink(code);
                    shortLinkBean.setLongLink(newLongLink);
                    shortLinkBean.setExpireTime(System.currentTimeMillis() + config.EXPIRE_SEC * 1000);
                    guavaCacheHelper.put(code, shortLinkBean);
                    // 把短链接放入布隆过滤器
                    bloomFilterHelper.put(code);

                }
                // 未冲突，已存在数据，不做处理，既不放到缓存中，也不放到过滤器中
                else {
                    // 记录日志
                    log.info("已存在： code " + code + " ; longLink: " + longLink);
                }
            }
        }

        return code;
    }

    @Override
    public String getLongLink(String shortLink) {
        // 从缓存中获取对象
        ShortLinkBean shortLinkBean = (ShortLinkBean) guavaCacheHelper.get(shortLink);
        String longLink = shortLinkBean.getLongLink();
        // 如果不存在Hash冲突标记，则直接返回长链接
        if (!longLink.startsWith(DuplicatedEnum.DUPLICATED.getKey()) && !longLink.startsWith(
                DuplicatedEnum.OH_MY_GOD.getKey())) {
            return longLink;
        }
        // 否则去掉冲突标记后再返回
        else {
            log.warn("去掉冲突标记后再返回：" + longLink);
            longLink = longLink.replace(DuplicatedEnum.DUPLICATED + "_", "");
            longLink = longLink.replace(DuplicatedEnum.OH_MY_GOD + "_", "");
        }
        return longLink;
    }
}

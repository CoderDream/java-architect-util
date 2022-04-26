package com.coderdream.bean;

import lombok.Data;

/**
 * @author ：CoderDream
 * @date ：Created in 2022/4/26 20:07
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
@Data
public class ShortLinkBean {

    /**
     * id
     */
    private Long id;

    /**
     * 长连接
     */
    private String longLink;

    /**
     * 长连接对应的短链接
     */
    private String shortLink;

    /**
     * 短链接过期时间
     */
    private Long expireTime;

}

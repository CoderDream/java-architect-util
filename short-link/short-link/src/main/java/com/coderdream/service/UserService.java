package com.coderdream.service;


import com.coderdream.bean.Link;
import com.coderdream.bean.User;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface UserService {
    String putLink(String longLink);
    String getLink(String shortLink);

    User getById(Long id);

    User getUser(User queryParam, int[] arr, String str);
}

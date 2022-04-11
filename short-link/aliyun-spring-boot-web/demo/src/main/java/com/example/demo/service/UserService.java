package com.example.demo.service;


import com.example.demo.bean.User;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public interface UserService {
    User getById(Long id);

    User getUser(User queryParam, int[] arr, String str);
}

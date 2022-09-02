package com.coderdream.swagger2;



import org.springframework.ui.Model;


/*
 *
 * @author paida 派哒 zeyu.pzy@alibaba-inc.com
 * @date 2020/10/27
 */


public interface GetUserInfoService {

    void getUserInfoById(String id, Model model);

}

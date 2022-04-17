package com.debug.middleware.server.entity;/**
 * Created by Administrator on 2019/3/16.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户充值记录
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/16 21:40
 **/
@Data
@ToString
public class PhoneUser implements Serializable{

    private String phone;
    private Double fare;

    public PhoneUser() {
    }

    public PhoneUser(String phone, Double fare) {
        this.phone = phone;
        this.fare = fare;
    }

    //手机号相同，代表充值记录重复(只适用于特殊的排名需要)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneUser phoneUser = (PhoneUser) o;

        return phone != null ? phone.equals(phoneUser.phone) : phoneUser.phone == null;
    }

    @Override
    public int hashCode() {
        return phone != null ? phone.hashCode() : 0;
    }
}






























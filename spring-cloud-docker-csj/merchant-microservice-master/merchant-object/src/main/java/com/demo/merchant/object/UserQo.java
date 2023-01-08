package com.demo.merchant.object;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserQo extends PageQo implements java.io.Serializable{
    private Long id;
    private String name;
    private String password;
    private String email;
    private Integer sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    private List<RoleQo> roles = new ArrayList<>();

    private MerchantQo merchant;

    public UserQo() {
    }

    public void addRole(RoleQo roleQo){
        this.roles.add(roleQo);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<RoleQo> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleQo> roles) {
        this.roles = roles;
    }

    public MerchantQo getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantQo merchant) {
        this.merchant = merchant;
    }
}

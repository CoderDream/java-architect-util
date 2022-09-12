package com.coderdream.mybatisplusdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author CoderDream
 * @since 2022-09-12
 */
@TableName("t_demo")
@ApiModel(value = "Demo对象", description = "")
public class Demo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("姓名")
    private String userName;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("逻辑删除")
    private Integer isDeleted;

    @ApiModelProperty("性别")
    private Integer sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Demo{" +
            "id = " + id +
            ", userName = " + userName +
            ", age = " + age +
            ", email = " + email +
            ", isDeleted = " + isDeleted +
            ", sex = " + sex +
        "}";
    }
}

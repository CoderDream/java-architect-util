package com.coderdream.mybatisplusdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author CoderDream
 * @since 2023-02-27
 */
@TableName("t_demo_copy")
@ApiModel(value = "DemoCopy对象", description = "")
public class DemoCopy implements Serializable {

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

    @ApiModelProperty("体重")
    private Object weight;

    @ApiModelProperty("身高")
    private BigDecimal height;

    @ApiModelProperty("生日")
    private LocalDateTime birthday;

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

    public Object getWeight() {
        return weight;
    }

    public void setWeight(Object weight) {
        this.weight = weight;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "DemoCopy{" +
            "id = " + id +
            ", userName = " + userName +
            ", age = " + age +
            ", email = " + email +
            ", isDeleted = " + isDeleted +
            ", sex = " + sex +
            ", weight = " + weight +
            ", height = " + height +
            ", birthday = " + birthday +
        "}";
    }
}

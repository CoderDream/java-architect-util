package com.demo.manage.object;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class OperatorsVo extends PageVo implements java.io.Serializable{
    private String id;
    private String name;
    private String email;
    private Integer sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;
    private String password;

    private DepartmentVo departmentVo;
    private List<PartVo> partVos;

    public OperatorsVo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public DepartmentVo getDepartmentVo() {
        return departmentVo;
    }

    public void setDepartmentVo(DepartmentVo departmentVo) {
        this.departmentVo = departmentVo;
    }

    public List<PartVo> getPartVos() {
        return partVos;
    }

    public void setPartVos(List<PartVo> partVos) {
        this.partVos = partVos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

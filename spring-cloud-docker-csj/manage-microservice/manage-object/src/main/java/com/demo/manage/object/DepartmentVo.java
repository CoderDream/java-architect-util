package com.demo.manage.object;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DepartmentVo extends PageVo implements java.io.Serializable{
    private String id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    public DepartmentVo() {
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

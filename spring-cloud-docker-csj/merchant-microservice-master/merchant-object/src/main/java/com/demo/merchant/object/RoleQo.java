package com.demo.merchant.object;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class RoleQo extends PageQo implements java.io.Serializable{
    private Long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;
    private List<ResourceQo> resources;

    public RoleQo() {
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ResourceQo> getResources() {
        return resources;
    }

    public void setResources(List<ResourceQo> resources) {
        this.resources = resources;
    }
}

package com.demo.merchant.object;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class ResourceQo extends PageQo implements java.io.Serializable{
    private Long id;
    private String name;
    private String url;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    private ModelQo model;

    public ResourceQo() {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public ModelQo getModel() {
        return model;
    }

    public void setModel(ModelQo model) {
        this.model = model;
    }
}

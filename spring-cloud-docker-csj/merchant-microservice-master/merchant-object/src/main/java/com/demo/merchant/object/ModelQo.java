package com.demo.merchant.object;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class ModelQo extends PageQo implements java.io.Serializable{
    private Long id;
    private String name;
    private String host;
    private String icon;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;
    private List<ResourceQo> resources;
    private KindQo kind;

    public ModelQo() {
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public KindQo getKind() {
        return kind;
    }

    public void setKind(KindQo kind) {
        this.kind = kind;
    }
}

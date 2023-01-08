package com.demo.merchant.object;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class KindQo extends PageQo implements java.io.Serializable{
    private Long id;
    private String name;
    private String link;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    public KindQo() {
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

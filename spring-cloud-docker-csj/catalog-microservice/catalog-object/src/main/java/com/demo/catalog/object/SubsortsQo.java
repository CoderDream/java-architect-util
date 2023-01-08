package com.demo.catalog.object;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SubsortsQo extends PageQo{

    private Long id;
    private String name;
    private String operator;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;


    public SubsortsQo() {
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}

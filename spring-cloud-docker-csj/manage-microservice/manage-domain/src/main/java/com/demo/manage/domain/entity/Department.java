package com.demo.manage.domain.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_department")
public class Department extends IdEntity implements java.io.Serializable{
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created", columnDefinition = "timestamp default current_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Department() {
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

package com.demo.merchant.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_model")
public class Model implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String host;
    private String icon;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created", columnDefinition = "timestamp default current_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @ManyToOne
    @JoinColumn(name = "kid")
    @JsonIgnore
    private Kind kind;

    public Model() {
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

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }
}

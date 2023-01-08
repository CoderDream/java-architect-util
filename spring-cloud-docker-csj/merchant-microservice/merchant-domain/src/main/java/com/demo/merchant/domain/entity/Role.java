package com.demo.merchant.domain.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_role")
public class Role implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created", columnDefinition = "timestamp default current_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @ManyToMany(cascade = {}, fetch = FetchType.EAGER)
    @JoinTable(name = "role_resource",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id")})
    private List<Resource> resources;

    public Role() {
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}

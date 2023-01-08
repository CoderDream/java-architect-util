package com.demo.manage.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_operator")
public class Operators extends IdEntity implements java.io.Serializable{
    private String name;
    private String email;
    private Integer sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created", columnDefinition = "timestamp default current_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    private String password;

    @ManyToOne
    @JoinColumn(name = "did")
    @JsonBackReference
    private Department department;

    @ManyToMany(cascade = {}, fetch = FetchType.EAGER)
    @JoinTable(name = "operator_part",
            joinColumns = {@JoinColumn(name = "operator_id")},
            inverseJoinColumns = {@JoinColumn(name = "part_id")})
    private List<Part> parts = new ArrayList<>();

    public Operators() {
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

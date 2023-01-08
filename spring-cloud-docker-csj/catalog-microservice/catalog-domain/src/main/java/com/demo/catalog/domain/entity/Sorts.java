package com.demo.catalog.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_sorts")
public class Sorts implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String operator;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created", columnDefinition = "timestamp default current_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @OneToMany(fetch = FetchType.EAGER)
    @OrderBy("created asc")
    @JoinTable(name = "sorts_subs",
            joinColumns = {@JoinColumn(name = "sorts_id")},
            inverseJoinColumns = {@JoinColumn(name = "subs_id")})
    @JsonIgnore
    private Set<Subsorts> subsortses = new HashSet<>();

    public Sorts() {
    }

    public void addSubsorts(Subsorts subsorts){
        subsortses.add(subsorts);
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

    public Set<Subsorts> getSubsortses() {
        return subsortses;
    }

    public void setSubsortses(Set<Subsorts> subsortses) {
        this.subsortses = subsortses;
    }
}

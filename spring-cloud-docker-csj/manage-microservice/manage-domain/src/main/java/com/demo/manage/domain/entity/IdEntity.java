package com.demo.manage.domain.entity;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class IdEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

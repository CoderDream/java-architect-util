package com.demo.goods.domain.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_picture")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "path_info")
    private String pathInfo;
    @Column(name = "file_name")
    private String fileName;
    private int width;
    private int height;
    private String flag;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created", columnDefinition = "timestamp default current_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    private Long merchantid;

    public Picture() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPathInfo() {
        return pathInfo;
    }

    public void setPathInfo(String pathInfo) {
        this.pathInfo = pathInfo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(Long merchantid) {
        this.merchantid = merchantid;
    }
}

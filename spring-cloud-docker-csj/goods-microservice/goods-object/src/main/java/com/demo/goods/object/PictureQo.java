package com.demo.goods.object;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PictureQo extends PageQo{
    private Long id;
    private String pathInfo;
    private String fileName;
    private int width;
    private int height;
    private String flag;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;
    private Long merchantid;

    public PictureQo() {
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

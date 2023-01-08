package com.feng.bean;

import java.util.Date;

/**
 * @ClassName PhotoSeaMainModel
 * @Description TODO
 * @Author admin
 * @Date 2021/4/8 12:45
 * @Version 1.0
 */
public class PhotoSeaMainModel {
    private Integer userId;
    private String title;
    private String fileName;
    private String fileOss;
    private Date createTime;
    private Integer versionId;
    private Integer dataFlag;
    private Integer useFlag;
    private Date timeStamp;
    private Integer start;
    private Integer pageSize;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileOss() {
        return fileOss;
    }

    public void setFileOss(String fileOss) {
        this.fileOss = fileOss;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public Integer getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(Integer dataFlag) {
        this.dataFlag = dataFlag;
    }

    public Integer getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(Integer useFlag) {
        this.useFlag = useFlag;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}

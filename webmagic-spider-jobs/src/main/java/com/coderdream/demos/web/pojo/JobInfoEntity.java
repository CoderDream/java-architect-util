package com.coderdream.demos.web.pojo;



import javax.persistence.*;
import java.util.Objects;

/**
 * @ClassName JobInfoEntity
 * @Description TODO
 * @Author lisonglin
 * @Date 2021/5/6 0:23
 * @Version 1.0
 */
@Entity
@Table(name = "job_info", schema = "test")
public class JobInfoEntity {
    // 主键id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // 公司名称
    private String companyName;
    // 公司联系方式
    private String companyAddr;
    // 公司信息
    private String companyInfo;
    // 职位名称
    private String jobName;
    // 工作地点
    private String jobAddr;
    // 职位信息
    private String jobInfo;
    // 薪资范围，最小
    private Integer salaryMin;
    // 薪资范围，最大
    private Integer salaryMax;
    // 关键技术点
    private String technology;
    // 招聘信息详情页
    private String url;
    // 职位最近发布时间
    private String time;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "company_name", nullable = true, length = 100)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "company_addr", nullable = true, length = 200)
    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    @Basic
    @Column(name = "company_info", nullable = true, length = -1)
    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    @Basic
    @Column(name = "job_name", nullable = true, length = 100)
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Basic
    @Column(name = "job_addr", nullable = true, length = 50)
    public String getJobAddr() {
        return jobAddr;
    }

    public void setJobAddr(String jobAddr) {
        this.jobAddr = jobAddr;
    }

    @Basic
    @Column(name = "job_info", nullable = true, length = -1)
    public String getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(String jobInfo) {
        this.jobInfo = jobInfo;
    }

    @Basic
    @Column(name = "salary_min", nullable = true)
    public Integer getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Integer salaryMin) {
        this.salaryMin = salaryMin;
    }

    @Basic
    @Column(name = "salary_max", nullable = true)
    public Integer getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Integer salaryMax) {
        this.salaryMax = salaryMax;
    }

    @Basic
    @Column(name = "technology", nullable = true, length = 200)
    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    @Basic
    @Column(name = "url", nullable = true, length = 150)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "time", nullable = true, length = 10)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobInfoEntity that = (JobInfoEntity) o;
        return id == that.id && Objects.equals(companyName, that.companyName) && Objects.equals(companyAddr,
            that.companyAddr) && Objects.equals(companyInfo, that.companyInfo) && Objects.equals(jobName,
            that.jobName) && Objects.equals(jobAddr, that.jobAddr) && Objects.equals(jobInfo, that.jobInfo) && Objects.equals(salaryMin, that.salaryMin) && Objects.equals(salaryMax, that.salaryMax) && Objects.equals(technology, that.technology) && Objects.equals(url, that.url) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, companyAddr, companyInfo, jobName, jobAddr, jobInfo, salaryMin, salaryMax
            , technology, url, time);
    }
}

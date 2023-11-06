package com.coderdream.freeapps.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * job_info
 * @TableName t_job_info
 */
@TableName(value ="t_job_info")
@Data
public class JobInfoEntity implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * wk9r4490ZT4M9-g1wyVzFkEY-3mIN_mT0EykIZuDPP9fe1uBAxYIN70Js-bxEU2dg2Ll0wb222wzzNqLiSPFAGhq2ndIy26YBKL-ANRCeOiWEtYBM5Li3j2CMsrTxMbSKkWIhsOU-Os4qe1h4BvhXKLrTNDhXixaLIS-3jm6rw6jI-V1
     */
    private String securityId;

    /**
     * https://img.bosszhipin.com/beijin/upload/avatar/20210617/cc4c594ac19105cdf36c10ca6bc5ba2e9ba5ba7f86bc862108e87dcbf3327ee4_s.png
     */
    private String bossAvatar;

    /**
     * 3
     */
    private Long bossCert;

    /**
     * 9f3723f0cb8073080nd83ti5FFFU
     */
    private String encryptBossId;

    /**
     * 余女士
     */
    private String bossName;

    /**
     * HR
     */
    private String bossTitle;

    /**
     * 0
     */
    private Long goldHunter;

    /**
     * true
     */
    private String bossOnline;

    /**
     * 124fa3972a8c7c7f1XN_2t21GFZZ
     */
    private String encryptJobId;

    /**
     * 339235632
     */
    private Long expectId;

    /**
     * 高级java开发工程师
     */
    private String jobName;

    /**
     * 14EcEQZYxzC.search.1
     */
    private String lid;

    /**
     * 薪水最小值
     */
    @ApiModelProperty("薪水最小值")
    @TableField(value = "salary_min")
    private Integer salaryMin;

    /**
     * 薪水最小值
     */
    @ApiModelProperty("薪水最小值")
    @TableField(value = "salary_max")
    private Integer salaryMax;

    /**
     * 薪水最小值
     */
    @ApiModelProperty("薪水最小值")
    @TableField(value = "salary_avg")
    private Integer salaryAvg;

    /**
     * 18-30K
     */
    private String salaryDesc;

    /**
     *
     */
    private String jobLabels;

    /**
     * 1
     */
    private Long jobValidStatus;

    /**
     *
     */
    private String iconWord;

    /**
     *
     */
    private String skills;

    /**
     * 最大工作年限
     */
    @ApiModelProperty("薪水最小值")
    @TableField(value = "job_experience_max")
    private Integer jobExperienceMax;

    /**
     * 5-10年
     */
    private String jobExperience;

    /**
     *
     */
    private String daysPerWeekDesc;

    /**
     *
     */
    private String leastMonthDesc;

    /**
     * 本科
     */
    private String jobDegree;

    /**
     * 武汉
     */
    private String cityName;

    /**
     * 洪山区
     */
    private String areaDistrict;

    /**
     * 南湖
     */
    private String businessDistrict;

    /**
     * 0
     */
    private Long jobType;

    /**
     * 0
     */
    private Long proxyJob;

    /**
     * 0
     */
    private Long proxyType;

    /**
     * 0
     */
    private Long anonymous;

    /**
     * 0
     */
    private Long outland;

    /**
     * 0
     */
    private Long optimal;

    /**
     * 1
     */
    private Long itemId;

    /**
     * 101200255
     */
    private Long city;

    /**
     * 0
     */
    private Long isShield;

    /**
     * false
     */
    private String atsDirectPost;

    /**
     * null
     */
    private String gps;

    /**
     * 1684887134000
     */
    private Long lastModifyTime;

    /**
     * 99dc1c6a5361f74c1nV_0tu0Fw~~
     */
    private String encryptBrandId;

    /**
     * 兆信股份
     */
    private String brandName;

    /**
     * https://img.bosszhipin.com/beijin/upload/com/workfeel/20210601/ef6e5a0d06cd5eaa2841a6e75768650f3fd56dace83b705ea9525279020bc443.jpg
     */
    private String brandLogo;

    /**
     * 已上市
     */
    private String brandStageName;

    /**
     * 移动互联网
     */
    private String brandIndustry;

    /**
     * 公司最大规模
     */
    @ApiModelProperty("公司最大规模")
    @TableField(value = "brand_scale")
    private Integer brandScale;

    /**
     * 255-499人
     */
    private String brandScaleName;

    /**
     *
     */
    private String welfareList;

    /**
     * 255019
     */
    private Long industry;

    /**
     * false
     */
    private String contact;

    /**
     * 创建日期
     */
    private Date createDate;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        JobInfoEntity other = (JobInfoEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSecurityId() == null ? other.getSecurityId() == null : this.getSecurityId().equals(other.getSecurityId()))
            && (this.getBossAvatar() == null ? other.getBossAvatar() == null : this.getBossAvatar().equals(other.getBossAvatar()))
            && (this.getBossCert() == null ? other.getBossCert() == null : this.getBossCert().equals(other.getBossCert()))
            && (this.getEncryptBossId() == null ? other.getEncryptBossId() == null : this.getEncryptBossId().equals(other.getEncryptBossId()))
            && (this.getBossName() == null ? other.getBossName() == null : this.getBossName().equals(other.getBossName()))
            && (this.getBossTitle() == null ? other.getBossTitle() == null : this.getBossTitle().equals(other.getBossTitle()))
            && (this.getGoldHunter() == null ? other.getGoldHunter() == null : this.getGoldHunter().equals(other.getGoldHunter()))
            && (this.getBossOnline() == null ? other.getBossOnline() == null : this.getBossOnline().equals(other.getBossOnline()))
            && (this.getEncryptJobId() == null ? other.getEncryptJobId() == null : this.getEncryptJobId().equals(other.getEncryptJobId()))
            && (this.getExpectId() == null ? other.getExpectId() == null : this.getExpectId().equals(other.getExpectId()))
            && (this.getJobName() == null ? other.getJobName() == null : this.getJobName().equals(other.getJobName()))
            && (this.getLid() == null ? other.getLid() == null : this.getLid().equals(other.getLid()))
            && (this.getSalaryDesc() == null ? other.getSalaryDesc() == null : this.getSalaryDesc().equals(other.getSalaryDesc()))
            && (this.getJobLabels() == null ? other.getJobLabels() == null : this.getJobLabels().equals(other.getJobLabels()))
            && (this.getJobValidStatus() == null ? other.getJobValidStatus() == null : this.getJobValidStatus().equals(other.getJobValidStatus()))
            && (this.getIconWord() == null ? other.getIconWord() == null : this.getIconWord().equals(other.getIconWord()))
            && (this.getSkills() == null ? other.getSkills() == null : this.getSkills().equals(other.getSkills()))
            && (this.getJobExperience() == null ? other.getJobExperience() == null : this.getJobExperience().equals(other.getJobExperience()))
            && (this.getDaysPerWeekDesc() == null ? other.getDaysPerWeekDesc() == null : this.getDaysPerWeekDesc().equals(other.getDaysPerWeekDesc()))
            && (this.getLeastMonthDesc() == null ? other.getLeastMonthDesc() == null : this.getLeastMonthDesc().equals(other.getLeastMonthDesc()))
            && (this.getJobDegree() == null ? other.getJobDegree() == null : this.getJobDegree().equals(other.getJobDegree()))
            && (this.getCityName() == null ? other.getCityName() == null : this.getCityName().equals(other.getCityName()))
            && (this.getAreaDistrict() == null ? other.getAreaDistrict() == null : this.getAreaDistrict().equals(other.getAreaDistrict()))
            && (this.getBusinessDistrict() == null ? other.getBusinessDistrict() == null : this.getBusinessDistrict().equals(other.getBusinessDistrict()))
            && (this.getJobType() == null ? other.getJobType() == null : this.getJobType().equals(other.getJobType()))
            && (this.getProxyJob() == null ? other.getProxyJob() == null : this.getProxyJob().equals(other.getProxyJob()))
            && (this.getProxyType() == null ? other.getProxyType() == null : this.getProxyType().equals(other.getProxyType()))
            && (this.getAnonymous() == null ? other.getAnonymous() == null : this.getAnonymous().equals(other.getAnonymous()))
            && (this.getOutland() == null ? other.getOutland() == null : this.getOutland().equals(other.getOutland()))
            && (this.getOptimal() == null ? other.getOptimal() == null : this.getOptimal().equals(other.getOptimal()))
            && (this.getItemId() == null ? other.getItemId() == null : this.getItemId().equals(other.getItemId()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getIsShield() == null ? other.getIsShield() == null : this.getIsShield().equals(other.getIsShield()))
            && (this.getAtsDirectPost() == null ? other.getAtsDirectPost() == null : this.getAtsDirectPost().equals(other.getAtsDirectPost()))
            && (this.getGps() == null ? other.getGps() == null : this.getGps().equals(other.getGps()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getEncryptBrandId() == null ? other.getEncryptBrandId() == null : this.getEncryptBrandId().equals(other.getEncryptBrandId()))
            && (this.getBrandName() == null ? other.getBrandName() == null : this.getBrandName().equals(other.getBrandName()))
            && (this.getBrandLogo() == null ? other.getBrandLogo() == null : this.getBrandLogo().equals(other.getBrandLogo()))
            && (this.getBrandStageName() == null ? other.getBrandStageName() == null : this.getBrandStageName().equals(other.getBrandStageName()))
            && (this.getBrandIndustry() == null ? other.getBrandIndustry() == null : this.getBrandIndustry().equals(other.getBrandIndustry()))
            && (this.getBrandScaleName() == null ? other.getBrandScaleName() == null : this.getBrandScaleName().equals(other.getBrandScaleName()))
            && (this.getWelfareList() == null ? other.getWelfareList() == null : this.getWelfareList().equals(other.getWelfareList()))
            && (this.getIndustry() == null ? other.getIndustry() == null : this.getIndustry().equals(other.getIndustry()))
            && (this.getContact() == null ? other.getContact() == null : this.getContact().equals(other.getContact()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSecurityId() == null) ? 0 : getSecurityId().hashCode());
        result = prime * result + ((getBossAvatar() == null) ? 0 : getBossAvatar().hashCode());
        result = prime * result + ((getBossCert() == null) ? 0 : getBossCert().hashCode());
        result = prime * result + ((getEncryptBossId() == null) ? 0 : getEncryptBossId().hashCode());
        result = prime * result + ((getBossName() == null) ? 0 : getBossName().hashCode());
        result = prime * result + ((getBossTitle() == null) ? 0 : getBossTitle().hashCode());
        result = prime * result + ((getGoldHunter() == null) ? 0 : getGoldHunter().hashCode());
        result = prime * result + ((getBossOnline() == null) ? 0 : getBossOnline().hashCode());
        result = prime * result + ((getEncryptJobId() == null) ? 0 : getEncryptJobId().hashCode());
        result = prime * result + ((getExpectId() == null) ? 0 : getExpectId().hashCode());
        result = prime * result + ((getJobName() == null) ? 0 : getJobName().hashCode());
        result = prime * result + ((getLid() == null) ? 0 : getLid().hashCode());
        result = prime * result + ((getSalaryDesc() == null) ? 0 : getSalaryDesc().hashCode());
        result = prime * result + ((getJobLabels() == null) ? 0 : getJobLabels().hashCode());
        result = prime * result + ((getJobValidStatus() == null) ? 0 : getJobValidStatus().hashCode());
        result = prime * result + ((getIconWord() == null) ? 0 : getIconWord().hashCode());
        result = prime * result + ((getSkills() == null) ? 0 : getSkills().hashCode());
        result = prime * result + ((getJobExperience() == null) ? 0 : getJobExperience().hashCode());
        result = prime * result + ((getDaysPerWeekDesc() == null) ? 0 : getDaysPerWeekDesc().hashCode());
        result = prime * result + ((getLeastMonthDesc() == null) ? 0 : getLeastMonthDesc().hashCode());
        result = prime * result + ((getJobDegree() == null) ? 0 : getJobDegree().hashCode());
        result = prime * result + ((getCityName() == null) ? 0 : getCityName().hashCode());
        result = prime * result + ((getAreaDistrict() == null) ? 0 : getAreaDistrict().hashCode());
        result = prime * result + ((getBusinessDistrict() == null) ? 0 : getBusinessDistrict().hashCode());
        result = prime * result + ((getJobType() == null) ? 0 : getJobType().hashCode());
        result = prime * result + ((getProxyJob() == null) ? 0 : getProxyJob().hashCode());
        result = prime * result + ((getProxyType() == null) ? 0 : getProxyType().hashCode());
        result = prime * result + ((getAnonymous() == null) ? 0 : getAnonymous().hashCode());
        result = prime * result + ((getOutland() == null) ? 0 : getOutland().hashCode());
        result = prime * result + ((getOptimal() == null) ? 0 : getOptimal().hashCode());
        result = prime * result + ((getItemId() == null) ? 0 : getItemId().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getIsShield() == null) ? 0 : getIsShield().hashCode());
        result = prime * result + ((getAtsDirectPost() == null) ? 0 : getAtsDirectPost().hashCode());
        result = prime * result + ((getGps() == null) ? 0 : getGps().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getEncryptBrandId() == null) ? 0 : getEncryptBrandId().hashCode());
        result = prime * result + ((getBrandName() == null) ? 0 : getBrandName().hashCode());
        result = prime * result + ((getBrandLogo() == null) ? 0 : getBrandLogo().hashCode());
        result = prime * result + ((getBrandStageName() == null) ? 0 : getBrandStageName().hashCode());
        result = prime * result + ((getBrandIndustry() == null) ? 0 : getBrandIndustry().hashCode());
        result = prime * result + ((getBrandScaleName() == null) ? 0 : getBrandScaleName().hashCode());
        result = prime * result + ((getWelfareList() == null) ? 0 : getWelfareList().hashCode());
        result = prime * result + ((getIndustry() == null) ? 0 : getIndustry().hashCode());
        result = prime * result + ((getContact() == null) ? 0 : getContact().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", jobInfoId=").append(id);
        sb.append(", securityId=").append(securityId);
        sb.append(", bossAvatar=").append(bossAvatar);
        sb.append(", bossCert=").append(bossCert);
        sb.append(", encryptBossId=").append(encryptBossId);
        sb.append(", bossName=").append(bossName);
        sb.append(", bossTitle=").append(bossTitle);
        sb.append(", goldHunter=").append(goldHunter);
        sb.append(", bossOnline=").append(bossOnline);
        sb.append(", encryptJobId=").append(encryptJobId);
        sb.append(", expectId=").append(expectId);
        sb.append(", jobName=").append(jobName);
        sb.append(", lid=").append(lid);
        sb.append(", salaryDesc=").append(salaryDesc);
        sb.append(", jobLabels=").append(jobLabels);
        sb.append(", jobValidStatus=").append(jobValidStatus);
        sb.append(", iconWord=").append(iconWord);
        sb.append(", skills=").append(skills);
        sb.append(", jobExperience=").append(jobExperience);
        sb.append(", daysPerWeekDesc=").append(daysPerWeekDesc);
        sb.append(", leastMonthDesc=").append(leastMonthDesc);
        sb.append(", jobDegree=").append(jobDegree);
        sb.append(", cityName=").append(cityName);
        sb.append(", areaDistrict=").append(areaDistrict);
        sb.append(", businessDistrict=").append(businessDistrict);
        sb.append(", jobType=").append(jobType);
        sb.append(", proxyJob=").append(proxyJob);
        sb.append(", proxyType=").append(proxyType);
        sb.append(", anonymous=").append(anonymous);
        sb.append(", outland=").append(outland);
        sb.append(", optimal=").append(optimal);
        sb.append(", itemId=").append(itemId);
        sb.append(", city=").append(city);
        sb.append(", isShield=").append(isShield);
        sb.append(", atsDirectPost=").append(atsDirectPost);
        sb.append(", gps=").append(gps);
        sb.append(", lastModifyTime=").append(lastModifyTime);
        sb.append(", encryptBrandId=").append(encryptBrandId);
        sb.append(", brandName=").append(brandName);
        sb.append(", brandLogo=").append(brandLogo);
        sb.append(", brandStageName=").append(brandStageName);
        sb.append(", brandIndustry=").append(brandIndustry);
        sb.append(", brandScaleName=").append(brandScaleName);
        sb.append(", welfareList=").append(welfareList);
        sb.append(", industry=").append(industry);
        sb.append(", contact=").append(contact);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}

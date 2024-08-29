package com.coderdream.demos.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author CoderDream
 * @TableName t_boss_job_detail
 */
@TableName(value = "t_boss_job_detail")
//@TableIndex(name = "encrypt_job_id_idx", unique = true, columnList = "encrypt_job_id")
@Data
public class BossJobDetail implements Serializable {

    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private String jobStatus;

    /**
     *
     */
    private String jobName;

    /**
     *
     */
    private String salary;

    /**
     *
     */
    private String experience;

    /**
     *
     */
    private String degree;

    /**
     *
     */
    private String freeTags;

    /**
     *
     */
    private String companyTitle;

    /**
     *
     */
    private String companyStage;

    /**
     *
     */
    private String companyScale;

    /**
     *
     */
    private String companyIndustry;

    /**
     *
     */
    private String jobKeywordList;

    /**
     *
     */
    private String jobSecText;

    /**
     *
     */
    private String bossName;

    /**
     *
     */
    private String bossActiveTime;

    /**
     *
     */
    private String bossRole;

    /**
     *
     */
    private String companyInfo;

    /**
     *
     */
    private String companyName;

    /**
     *
     */
    private String companyUser;

    /**
     *
     */
    private String resTime;

    /**
     *
     */
    private String companyType;

    /**
     *
     */
    private String manageState;

    /**
     *
     */
    private String companyFund;

    /**
     *
     */
    private String locationAddress;

    /**
     *
     */
    private String dataLat;

    private String encryptJobId;

    private String jobUrl;

    private Date createTime;

    private Date lastModifiedTime;

//     workInf.setUrl(page.getUrl().get());

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

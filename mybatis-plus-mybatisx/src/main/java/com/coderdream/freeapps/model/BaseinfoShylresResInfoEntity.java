package com.coderdream.freeapps.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 新水库系统水库基本信息
 * @TableName baseinfo_shylres_res_info
 */
@TableName(value ="baseinfo_shylres_res_info")
@Data
public class BaseinfoShylresResInfoEntity implements Serializable {
    /**
     * 主键 水库的唯一编码，由不同源头按优先级提取的唯一编码,然后统一生成32位
     */
    @TableId
    private String guid;

    /**
     * 水库编码 水库的唯一标识符编码
     */
    private String code;

    /**
     * 老库编码
     */
    private String skCode;

    /**
     * 水库名称 水库名称
     */
    private String name;

    /**
     * 来源系统
     */
    private String sysResource;

    /**
     * 采集时间 最近一次进入中心库的时间
     */
    private Date collectTime;

    /**
     * 更新时间 此条记录最后一次修改时间
     */
    private Date updateTime;

    /**
     * 创建时间 此条记录的创建时间
     */
    private Date createTime;

    /**
     * 是否失效 记录是否失效
     */
    private Integer isDeleted;

    /**
     * 备注 水库备注
     */
    private String note;

    /**
     * 水库简介 描述水库的一段文字
     */
    private String summary;

    /**
     * 主要建筑物级别 主要建筑物的工程级别，等同于主要建筑物的工程等别，关联字典表data_type字段中为工程等别的值域标识
     */
    private String majorLevel;

    /**
     * 地震反应谱特征周期 规准化的反应谱曲线开始下降点所对应的周期值,也称特征周期、卓越周期,是建筑场地自身的周期，关联字典表data_type字段中为地震反应谱特征周期等别的值域标识
     */
    private String characteristicPeriod;

    /**
     * 地震动峰值加速度 地震震动过程中，地表质点运动的加速度最大绝对值的等级划分，关联字典表data_type字段中为地震动峰值加速度等别的值域标识
     */
    private String acceleration;

    /**
     * 地震基本烈度 国家规定的，或经专门鉴定的工程所在地区场地地震烈度值，已关联字典表data_type字段中为地震基本烈度的值域标识
     */
    private String seismicDesignIntensity;

    /**
     * 抗震设计烈度 在基本烈度基础上确定的作为工程设防依据的地震烈度
     */
    private String baseEqIntensity;

    /**
     * 所属行业部门 归属的行业部门，已关联字典表data_type字段中为行业部门的值域标识
     */
    private String industryDept;

    /**
     * 左下角纬度(度) 水库最小外接矩形左下角北纬度数，采用2000国家大地坐标系（CGCS2000），如34.123456
     */
    private BigDecimal lowLeftLat;

    /**
     * 左下角经度(度) 水库最小外接矩形左下角东经度数，采用2000国家大地坐标系（CGCS2000），如123.654321
     */
    private BigDecimal lowLeftLong;

    /**
     * 右上角纬度(度) 水库最小外接矩形右上角北纬度数，其他规定同“左下角纬度”
     */
    private BigDecimal upRightLat;

    /**
     * 右上角经度(度) 水库最小外接矩形右上角东经度数，其他规定同“左下角经度”
     */
    private BigDecimal upRightLong;

    /**
     * 中心点纬度(度) 水库中心点北纬度数，采用2000国家大地坐标系（CGCS2000），如34.123456。
     */
    private String centerLat;

    /**
     * 中心点经度(度) 水库中心点东经度数，采用2000国家大地坐标系（CGCS2000），如124.123456。
     */
    private BigDecimal centerLong;

    /**
     * 水库所在位置 水库主坝详细位置的描述，所在的省（自治区、直辖市）、市（区、地、州、盟）、县（区、市、旗）、乡（镇）以及具体街（村）的名称。
     */
    private String resLoc;

    /**
     * 内部行政区划guid
     */
    private String interiorAdGuid;

    /**
     * 主管部门行政区划guid 主管部门所在行政区划guid
     */
    private String mgrAdGuid;

    /**
     * 行政区划guid 所在行政区划的guid
     */
    private String adGuid;

    /**
     * 流域名称（水利部） 所在流域的名称
     */
    private String basName;

    /**
     * 流域guid 所在流域的唯一编码
     */
    private String basGuid;

    /**
     * 河流名称（水利部） 河流名称
     */
    private String rvName;

    /**
     * 河流guid 所在流域的guid
     */
    private String rvGuid;

    /**
     * 建成时间 针对已建工程填写主体工程正式投入运行的日期;格式:YYYY -MM - DD;如:三峡大坝建成时间记录为2006-05-20。
     */
    private Date compDate;

    /**
     * 完工时间 验收合格时间，格式为: YYYY -MM - DD
     */
    private Date finishDate;

    /**
     * 开工时间 针对在建工程填写主体工程开工令上的日期;格式:YYYY -MM - DD;如:溪洛渡大坝开工时间记录为2005-12-16。
     */
    private Date startDate;

    /**
     * 工程建设情况 工程建设情况的代码，已关联字典表data_type字段中为工程建设情况的值域标识
     */
    private String engStat;

    /**
     * 工程规模 工程规模的代码，无法查阅工程设计文件的，参照SL252-2017中的水利水电工程分等指标表进行填写。已关联字典表data_type字段中为工程规模的值域标识
     */
    private String engScal;

    /**
     * 工程等别 按照工程设计文件中规定的等别填写其代码，关联字典表data_type字段中为工程等别的值域标识
     */
    private String engGrad;

    /**
     * 水库类别 按照水库效益来划分，关联字典表data_type字段中为水库类别的值域标识。
     */
    private String resCategory;

    /**
     * 水库功能 水库兴利的功能，已关联字典表data_type字段中为水库功能的值域标识
     */
    private String resFunc;

    /**
     * 水库类型 根据水库所处的地形条件不同进行分类，分为山区水库、丘陵水库、平原水库、滨海区水库、地下水库、其他类型。关联字典表data_type字段中为水库类型的值域标识
     */
    private String resType;

    /**
     * 注册登记时间（水利部） 在水利部全国水库运行管理系统里的注册登记时间
     */
    private Date resRegDate;

    /**
     * 其他行业注册号 其他行业注册号（电口的有，其他行业没有）
     */
    private String otherRegCode;

    /**
     * 注册登记码 在水利部全国水库运行管理系统里的注册登记时间
     */
    private String resRegCode;

    /**
     * 曾用名 曾用名
     */
    private String aliasName;

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
        BaseinfoShylresResInfoEntity other = (BaseinfoShylresResInfoEntity) that;
        return (this.getGuid() == null ? other.getGuid() == null : this.getGuid().equals(other.getGuid()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getSkCode() == null ? other.getSkCode() == null : this.getSkCode().equals(other.getSkCode()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getSysResource() == null ? other.getSysResource() == null : this.getSysResource().equals(other.getSysResource()))
            && (this.getCollectTime() == null ? other.getCollectTime() == null : this.getCollectTime().equals(other.getCollectTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getSummary() == null ? other.getSummary() == null : this.getSummary().equals(other.getSummary()))
            && (this.getMajorLevel() == null ? other.getMajorLevel() == null : this.getMajorLevel().equals(other.getMajorLevel()))
            && (this.getCharacteristicPeriod() == null ? other.getCharacteristicPeriod() == null : this.getCharacteristicPeriod().equals(other.getCharacteristicPeriod()))
            && (this.getAcceleration() == null ? other.getAcceleration() == null : this.getAcceleration().equals(other.getAcceleration()))
            && (this.getSeismicDesignIntensity() == null ? other.getSeismicDesignIntensity() == null : this.getSeismicDesignIntensity().equals(other.getSeismicDesignIntensity()))
            && (this.getBaseEqIntensity() == null ? other.getBaseEqIntensity() == null : this.getBaseEqIntensity().equals(other.getBaseEqIntensity()))
            && (this.getIndustryDept() == null ? other.getIndustryDept() == null : this.getIndustryDept().equals(other.getIndustryDept()))
            && (this.getLowLeftLat() == null ? other.getLowLeftLat() == null : this.getLowLeftLat().equals(other.getLowLeftLat()))
            && (this.getLowLeftLong() == null ? other.getLowLeftLong() == null : this.getLowLeftLong().equals(other.getLowLeftLong()))
            && (this.getUpRightLat() == null ? other.getUpRightLat() == null : this.getUpRightLat().equals(other.getUpRightLat()))
            && (this.getUpRightLong() == null ? other.getUpRightLong() == null : this.getUpRightLong().equals(other.getUpRightLong()))
            && (this.getCenterLat() == null ? other.getCenterLat() == null : this.getCenterLat().equals(other.getCenterLat()))
            && (this.getCenterLong() == null ? other.getCenterLong() == null : this.getCenterLong().equals(other.getCenterLong()))
            && (this.getResLoc() == null ? other.getResLoc() == null : this.getResLoc().equals(other.getResLoc()))
            && (this.getInteriorAdGuid() == null ? other.getInteriorAdGuid() == null : this.getInteriorAdGuid().equals(other.getInteriorAdGuid()))
            && (this.getMgrAdGuid() == null ? other.getMgrAdGuid() == null : this.getMgrAdGuid().equals(other.getMgrAdGuid()))
            && (this.getAdGuid() == null ? other.getAdGuid() == null : this.getAdGuid().equals(other.getAdGuid()))
            && (this.getBasName() == null ? other.getBasName() == null : this.getBasName().equals(other.getBasName()))
            && (this.getBasGuid() == null ? other.getBasGuid() == null : this.getBasGuid().equals(other.getBasGuid()))
            && (this.getRvName() == null ? other.getRvName() == null : this.getRvName().equals(other.getRvName()))
            && (this.getRvGuid() == null ? other.getRvGuid() == null : this.getRvGuid().equals(other.getRvGuid()))
            && (this.getCompDate() == null ? other.getCompDate() == null : this.getCompDate().equals(other.getCompDate()))
            && (this.getFinishDate() == null ? other.getFinishDate() == null : this.getFinishDate().equals(other.getFinishDate()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEngStat() == null ? other.getEngStat() == null : this.getEngStat().equals(other.getEngStat()))
            && (this.getEngScal() == null ? other.getEngScal() == null : this.getEngScal().equals(other.getEngScal()))
            && (this.getEngGrad() == null ? other.getEngGrad() == null : this.getEngGrad().equals(other.getEngGrad()))
            && (this.getResCategory() == null ? other.getResCategory() == null : this.getResCategory().equals(other.getResCategory()))
            && (this.getResFunc() == null ? other.getResFunc() == null : this.getResFunc().equals(other.getResFunc()))
            && (this.getResType() == null ? other.getResType() == null : this.getResType().equals(other.getResType()))
            && (this.getResRegDate() == null ? other.getResRegDate() == null : this.getResRegDate().equals(other.getResRegDate()))
            && (this.getOtherRegCode() == null ? other.getOtherRegCode() == null : this.getOtherRegCode().equals(other.getOtherRegCode()))
            && (this.getResRegCode() == null ? other.getResRegCode() == null : this.getResRegCode().equals(other.getResRegCode()))
            && (this.getAliasName() == null ? other.getAliasName() == null : this.getAliasName().equals(other.getAliasName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGuid() == null) ? 0 : getGuid().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getSkCode() == null) ? 0 : getSkCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSysResource() == null) ? 0 : getSysResource().hashCode());
        result = prime * result + ((getCollectTime() == null) ? 0 : getCollectTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getSummary() == null) ? 0 : getSummary().hashCode());
        result = prime * result + ((getMajorLevel() == null) ? 0 : getMajorLevel().hashCode());
        result = prime * result + ((getCharacteristicPeriod() == null) ? 0 : getCharacteristicPeriod().hashCode());
        result = prime * result + ((getAcceleration() == null) ? 0 : getAcceleration().hashCode());
        result = prime * result + ((getSeismicDesignIntensity() == null) ? 0 : getSeismicDesignIntensity().hashCode());
        result = prime * result + ((getBaseEqIntensity() == null) ? 0 : getBaseEqIntensity().hashCode());
        result = prime * result + ((getIndustryDept() == null) ? 0 : getIndustryDept().hashCode());
        result = prime * result + ((getLowLeftLat() == null) ? 0 : getLowLeftLat().hashCode());
        result = prime * result + ((getLowLeftLong() == null) ? 0 : getLowLeftLong().hashCode());
        result = prime * result + ((getUpRightLat() == null) ? 0 : getUpRightLat().hashCode());
        result = prime * result + ((getUpRightLong() == null) ? 0 : getUpRightLong().hashCode());
        result = prime * result + ((getCenterLat() == null) ? 0 : getCenterLat().hashCode());
        result = prime * result + ((getCenterLong() == null) ? 0 : getCenterLong().hashCode());
        result = prime * result + ((getResLoc() == null) ? 0 : getResLoc().hashCode());
        result = prime * result + ((getInteriorAdGuid() == null) ? 0 : getInteriorAdGuid().hashCode());
        result = prime * result + ((getMgrAdGuid() == null) ? 0 : getMgrAdGuid().hashCode());
        result = prime * result + ((getAdGuid() == null) ? 0 : getAdGuid().hashCode());
        result = prime * result + ((getBasName() == null) ? 0 : getBasName().hashCode());
        result = prime * result + ((getBasGuid() == null) ? 0 : getBasGuid().hashCode());
        result = prime * result + ((getRvName() == null) ? 0 : getRvName().hashCode());
        result = prime * result + ((getRvGuid() == null) ? 0 : getRvGuid().hashCode());
        result = prime * result + ((getCompDate() == null) ? 0 : getCompDate().hashCode());
        result = prime * result + ((getFinishDate() == null) ? 0 : getFinishDate().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEngStat() == null) ? 0 : getEngStat().hashCode());
        result = prime * result + ((getEngScal() == null) ? 0 : getEngScal().hashCode());
        result = prime * result + ((getEngGrad() == null) ? 0 : getEngGrad().hashCode());
        result = prime * result + ((getResCategory() == null) ? 0 : getResCategory().hashCode());
        result = prime * result + ((getResFunc() == null) ? 0 : getResFunc().hashCode());
        result = prime * result + ((getResType() == null) ? 0 : getResType().hashCode());
        result = prime * result + ((getResRegDate() == null) ? 0 : getResRegDate().hashCode());
        result = prime * result + ((getOtherRegCode() == null) ? 0 : getOtherRegCode().hashCode());
        result = prime * result + ((getResRegCode() == null) ? 0 : getResRegCode().hashCode());
        result = prime * result + ((getAliasName() == null) ? 0 : getAliasName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", guid=").append(guid);
        sb.append(", code=").append(code);
        sb.append(", skCode=").append(skCode);
        sb.append(", name=").append(name);
        sb.append(", sysResource=").append(sysResource);
        sb.append(", collectTime=").append(collectTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", note=").append(note);
        sb.append(", summary=").append(summary);
        sb.append(", majorLevel=").append(majorLevel);
        sb.append(", characteristicPeriod=").append(characteristicPeriod);
        sb.append(", acceleration=").append(acceleration);
        sb.append(", seismicDesignIntensity=").append(seismicDesignIntensity);
        sb.append(", baseEqIntensity=").append(baseEqIntensity);
        sb.append(", industryDept=").append(industryDept);
        sb.append(", lowLeftLat=").append(lowLeftLat);
        sb.append(", lowLeftLong=").append(lowLeftLong);
        sb.append(", upRightLat=").append(upRightLat);
        sb.append(", upRightLong=").append(upRightLong);
        sb.append(", centerLat=").append(centerLat);
        sb.append(", centerLong=").append(centerLong);
        sb.append(", resLoc=").append(resLoc);
        sb.append(", interiorAdGuid=").append(interiorAdGuid);
        sb.append(", mgrAdGuid=").append(mgrAdGuid);
        sb.append(", adGuid=").append(adGuid);
        sb.append(", basName=").append(basName);
        sb.append(", basGuid=").append(basGuid);
        sb.append(", rvName=").append(rvName);
        sb.append(", rvGuid=").append(rvGuid);
        sb.append(", compDate=").append(compDate);
        sb.append(", finishDate=").append(finishDate);
        sb.append(", startDate=").append(startDate);
        sb.append(", engStat=").append(engStat);
        sb.append(", engScal=").append(engScal);
        sb.append(", engGrad=").append(engGrad);
        sb.append(", resCategory=").append(resCategory);
        sb.append(", resFunc=").append(resFunc);
        sb.append(", resType=").append(resType);
        sb.append(", resRegDate=").append(resRegDate);
        sb.append(", otherRegCode=").append(otherRegCode);
        sb.append(", resRegCode=").append(resRegCode);
        sb.append(", aliasName=").append(aliasName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
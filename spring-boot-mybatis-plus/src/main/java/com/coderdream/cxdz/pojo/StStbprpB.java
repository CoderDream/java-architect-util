package com.coderdream.cxdz.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MoBai·杰
 * @since 2023-01-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StStbprpB对象", description="")
public class StStbprpB implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "STCD", type = IdType.ID_WORKER)
    private String stcd;

    @TableField("STNM")
    private String stnm;

    @TableField("RVNM")
    private String rvnm;

    @TableField("HNNM")
    private String hnnm;

    @TableField("BSNM")
    private String bsnm;

    @TableField("LGTD")
    private BigDecimal lgtd;

    @TableField("LTTD")
    private BigDecimal lttd;

    @TableField("STLC")
    private String stlc;

    @TableField("ADDVCD")
    private String addvcd;

    @TableField("DTMNM")
    private String dtmnm;

    @TableField("DTMEL")
    private BigDecimal dtmel;

    @TableField("DTPR")
    private BigDecimal dtpr;

    @TableField("STTP")
    private String sttp;

    @TableField("FRGRD")
    private String frgrd;

    @TableField("ESSTYM")
    private String esstym;

    @TableField("BGFRYM")
    private String bgfrym;

    @TableField("ATCUNIT")
    private String atcunit;

    @TableField("ADMAUTH")
    private String admauth;

    @TableField("LOCALITY")
    private String locality;

    @TableField("STBK")
    private String stbk;

    @TableField("STAZT")
    private BigDecimal stazt;

    @TableField("DSTRVM")
    private BigDecimal dstrvm;

    @TableField("DRNA")
    private BigDecimal drna;

    @TableField("PHCD")
    private String phcd;

    @TableField("USFL")
    private String usfl;

    @TableField("COMMENTS")
    private String comments;

    @TableField("MODITIME")
    private Date moditime;


}

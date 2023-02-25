package com.keepsoft.microservice.dto;

import com.keepsoft.microservice.validator.group.AddGroup;
import com.keepsoft.microservice.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("类型元数据信息")
public class StructureTypeMetaReqDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -66879324475172690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    @NotNull(message = "结构类型主键id不能为空", groups = {UpdateGroup.class})
    private Integer id;

    /**
     * 结构类型名称
     */
    @ApiModelProperty("结构类型名称")
    @NotNull(message = "结构类型名称不能为空", groups = {AddGroup.class})
    private String structureTypeName;

    /**
     * 结构类型标识
     */
    @ApiModelProperty("结构类型标识")
    @NotNull(message = "结构类型标识不能为空", groups = {AddGroup.class})
    private String structureTypeLabel;

    /**
     * 结构类型编码
     */
    @ApiModelProperty("结构类型编码")
    @NotNull(message = "结构类型编码不能为空", groups = {AddGroup.class})
    private String structureTypeCode;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date updateTime;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 结构描述
     */
    @ApiModelProperty("结构类型描述")
    @Size(max = 50, message = "结构类型描述必须50个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    private String remark;
}

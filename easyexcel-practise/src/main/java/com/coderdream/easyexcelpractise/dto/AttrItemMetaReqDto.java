package com.coderdream.easyexcelpractise.dto;

import com.coderdream.easyexcelpractise.validator.group.AddGroup;
import com.coderdream.easyexcelpractise.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("属性条目元数据信息请求DTO")
public class AttrItemMetaReqDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -66879324472172190L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    @NotNull(message = "属性条目主键ID不能为空", groups = {UpdateGroup.class})
    private Integer id;

    /**
     * 属性条目名称
     */
    @ApiModelProperty("属性条目名称")
    @NotNull(message = "属性条目名称不能为空", groups = {AddGroup.class})
    @Size(max = 20, message = "属性条目名称必须20个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    private String attrItemName;

    /**
     * 属性条目标识
     */
    @ApiModelProperty("属性条目标识")
    @NotNull(message = "属性条目标识不能为空", groups = {AddGroup.class})
    @Size(max = 20, message = "属性条目标识必须20个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    private String attrItemLabel;

    /**
     * 属性条目编码
     */
    @ApiModelProperty("属性条目编码")
    @NotNull(message = "属性条目编码不能为空", groups = {AddGroup.class})
    @Size(max = 20, message = "属性条目编码必须20个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    private String attrItemCode;

    /**
     * 属性条目全码
     */
    @ApiModelProperty("属性条目全码")
    @NotNull(message = "属性条目全码不能为空", groups = {AddGroup.class})
    @Size(max = 20, message = "属性条目全码必须20个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    private String attrItemFullCode;

    /**
     * 属性条目所属属性的编码
     */
    @ApiModelProperty("属性条目所属属性的名称")
    private String attrCode;

    /**
     * 属性条目所属属性的全码
     */
    @ApiModelProperty("属性条目所属属性的全码")
    @NotNull(message = "属性条目所属属性的全码", groups = {AddGroup.class})
    @Size(max = 20, message = "属性条目所属属性的全码必须20个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    private String attrFullCode;

    /**
     * 属性条目所属属性的名称
     */
    @ApiModelProperty("属性条目所属属性的名称")
    private String attrName;

    /**
     * 对象编码
     */
    @ApiModelProperty("对象编码")
    private String objectCode;

    /**
     * 对象全码
     */
    @ApiModelProperty("对象全码")
    @NotNull(message = "对象全码不能为空", groups = {AddGroup.class})
    @Size(max = 20, message = "对象全码必须20个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    private String objectFullCode;


    /**
     * 属性条目所属对象的编码
     */
    @ApiModelProperty("属性条目所属对象的编码")
    private String[] objectFullCodes;

    /**
     * 对象类型编码
     */
    @ApiModelProperty("对象类型类型编码")
    private String objectTypeCode;

    /**
     * 对象类型类型全码
     */
    @ApiModelProperty("对象类型类型全码")
    private String objectTypeFullCode;

    /**
     * 属性条目所属属性的编码
     */
    @ApiModelProperty("属性条目所属属性类型的编码")
    private String attrTypeFullCode;

    /**
     * 属性条目所属属性的编码
     */
    @ApiModelProperty("属性条目所属属性的编码")
    private String[] attrFullCodes;

    /**
     * 属性条目所属属性的名称
     */
    @ApiModelProperty("属性条目所属属性类型的名称")
    private String attrTypeName;

    /**
     * 属性条目所属属性类型的编码列表
     */
    @ApiModelProperty("属性条目所属属性类型的编码列表")
    private String[] attrTypeFullCodes;

    /**
     * 属性的算力级别，分为采集：collection、计算：calc、指标：key、决策：decision；
     */
    @ApiModelProperty("属性的算力级别")
    private String hashRateLevel;

    /**
     * 数据来源: info_system:系统来源；model:模型来源；literature: 文献来源；web:网络来源
     */
    @ApiModelProperty("数据来源")
    private String dataSource;

    /**
     * 属性类型所属的类型结构编码
     */
    @ApiModelProperty("属性类型所属的类型结构编码")
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
     * 属性条目描述
     */
    @ApiModelProperty("属性条目描述")
    @Size(max = 50, message = "属性条目描述必须50个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    private String remark;
}

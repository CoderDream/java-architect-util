package com.keepsoft.microservice.dto;

import com.keepsoft.microservice.validator.group.AddGroup;
import com.keepsoft.microservice.validator.group.DeleteGroup;
import com.keepsoft.microservice.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("属性元数据信息请求数据传输对象")
@ToString
public class AttrMetaReqDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -66879324475172690L;

    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    @NotNull(message = "主键ID不能为空", groups = {UpdateGroup.class, DeleteGroup.class})
    private Integer id;

    /**
     * 属性名称
     */
    @ApiModelProperty("属性名称")
    @NotNull(message = "属性名称不能为空", groups = {AddGroup.class})
    @Size(max = 20, message = "属性名称必须20个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    private String attrName;

    /**
     * 属性名称列表
     */
    @ApiModelProperty("属性名称列表")
    private List<String> attrNames;

    /**
     * 属性标识
     */
    @ApiModelProperty("属性标识")
    @NotNull(message = "属性标识不能为空", groups = {AddGroup.class})
    @Size(max = 20, message = "属性标识必须20个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    private String attrLabel;

    /**
     * 属性编码
     */
    @ApiModelProperty("属性编码")
    @NotNull(message = "属性编码不能为空", groups = {AddGroup.class})
    @Size(max = 20, message = "属性编码必须20个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    private String attrCode;

    /**
     * 属性全码
     */
    @ApiModelProperty("属性全码")
    @NotNull(message = "属性全码不能为空", groups = {AddGroup.class})
    @Size(max = 20, message = "属性全码必须20个字以内。", groups = {AddGroup.class, UpdateGroup.class, DeleteGroup.class})
    private String attrFullCode;

    /**
     * 数据类型
     */
    @ApiModelProperty("数据类型")
    @NotNull(message = "数据类型不能为空", groups = {AddGroup.class})
    private String dataType;

    /**
     * 公共标识，默认1公共，0私有
     */
    @ApiModelProperty("公共标识")
    @NotNull(message = "公共标识不能为空", groups = {AddGroup.class})
    private Integer commonFlag;

    /**
     * 显示标识，默认1显示，0隐藏
     */
    @ApiModelProperty("显示标识")
//    @NotNull(message = "显示标识不能为空", groups = {AddGroup.class}  = 1
    private Integer displayFlag;

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
     * 属性描述
     */
    @ApiModelProperty("属性描述")
    @Size(max = 50, message = "属性描述必须50个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    private String remark;

    /**
     * 属性类型编码
     */
    @ApiModelProperty("属性类型编码")
    private String attrTypeCode;

    /**
     * 属性类型全码
     */
    @ApiModelProperty("属性类型全码")
    @NotNull(message = "属性类型全码不能为空", groups = {AddGroup.class})
    @Size(max = 20, message = "属性类型全码必须20个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    private String attrTypeFullCode;

    /**
     * 属性所属类型名称
     */
    @ApiModelProperty("属性所属类型名称")
    private String attrTypeName;

    /**
     * 属性所属类型名称列表
     */
    @ApiModelProperty("属性所属类型名称列表")
    private String[] attrTypeNames;

    /**
     * 属性类型所属结构类型，基础属性 or 数据属性
     */
    @ApiModelProperty("属性类型所属结构类型")
    private String structureTypeCode;

    /**
     * 属性所属类型名称
     */
    @ApiModelProperty("属性所属结构类型名称")
    private String structureTypeName;

    /**
     * 对象类型编码
     */
    @ApiModelProperty("对象类型编码")
    private String objectTypeCode;

    /**
     * 对象类型全码
     */
    @ApiModelProperty("对象类型全码")
    private String objectTypeFullCode;

    /**
     * 对象类型名称
     */
    @ApiModelProperty("对象类型名称")
    private String objectTypeName;

    /**
     * 对象编码
     */
    @ApiModelProperty("对象编码")
    private String objectCode;

    /**
     * 对象全码
     */
    @ApiModelProperty("对象全码")
    private String objectFullCode;
}

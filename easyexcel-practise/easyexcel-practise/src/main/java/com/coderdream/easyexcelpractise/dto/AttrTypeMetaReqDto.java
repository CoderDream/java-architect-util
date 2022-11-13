package com.coderdream.easyexcelpractise.dto;

import com.coderdream.easyexcelpractise.validator.group.AddGroup;
import com.coderdream.easyexcelpractise.validator.group.DeleteGroup;
import com.coderdream.easyexcelpractise.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("属性类型元数据信息")
public class AttrTypeMetaReqDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -66879324475172690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    @NotNull(message = "属性类型元数据主键ID不能为空", groups = {UpdateGroup.class, DeleteGroup.class})
    private Integer id;

    /**
     * 属性类型名称
     */
    @ApiModelProperty("属性类型名称")
    @NotNull(message = "属性类型名称不能为空", groups = {AddGroup.class})
    @Size(max = 20, message = "属性类型名称必须20个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    private String attrTypeName;

    /**
     * 属性类型标识
     */
    @ApiModelProperty("属性类型标识")
    @NotNull(message = "类型标识不能为空", groups = {AddGroup.class})
    @Size(min = 0, max = 20, message = "属性类型标识必须20个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    //  @Pattern(regexp = "^[a-zA-Z_]+$", message = "属性类型英文标识只能输入字母和下划线。", groups = {AddGroup.class, UpdateGroup.class})
    private String attrTypeLabel;

    /**
     * 属性类型编码
     */
    @ApiModelProperty("属性类型编码")
    @NotNull(message = "属性类型编码不能为空", groups = {AddGroup.class})
   // @Pattern(regexp = "([A-Z0-9]{2})(^0{2})", message = "属性类型编码只能输入数字和大写字母，且不可以为00。", groups = {AddGroup.class, UpdateGroup.class})
    private String attrTypeCode;

    /**
     * 属性类型编码
     */
    @ApiModelProperty("属性类型全码")
    @NotNull(message = "属性类型全码不能为空", groups = {AddGroup.class, UpdateGroup.class, DeleteGroup.class})
   // @Pattern(regexp = "([A-Z0-9]{2})(^0{2})", message = "属性类型编码只能输入数字和大写字母，且不可以为00。", groups = {AddGroup.class, UpdateGroup.class})
    private String attrTypeFullCode;

    /**
     * 公共标识
     */
    @ApiModelProperty("公共标识")
    @NotNull(message = "公共标识不能为空", groups = {AddGroup.class})
    private Integer commonFlag;

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
     * 属性类型描述
     */
    @ApiModelProperty("属性类型描述")
    private String remark;

    /**
     * 对象类型全码
     */
    @ApiModelProperty("对象类型全码")
    @NotNull(message = "对象类型全码不能为空", groups = {AddGroup.class})
    private String objectTypeFullCode;

    /**
     * 属性类型所属结构类型，基础属性 or 数据属性
     */
    @ApiModelProperty("属性类型所属结构类型")
    @NotNull(message = "属性类型所属结构类型不能为空", groups = {AddGroup.class})
    private String structureTypeCode;
}

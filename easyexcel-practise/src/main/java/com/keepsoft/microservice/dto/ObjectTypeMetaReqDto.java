package com.keepsoft.microservice.dto;

import com.keepsoft.microservice.validator.group.AddGroup;
import com.keepsoft.microservice.validator.group.DeleteGroup;
import com.keepsoft.microservice.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("类型元数据信息请求数据传输对象")
public class ObjectTypeMetaReqDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -66879324475172690L;

    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    @NotNull(message = "类型名称不能为空", groups = {UpdateGroup.class, DeleteGroup.class})
    private Integer id;

    /**
     * 类型名称
     */
    @ApiModelProperty("类型名称")
    @NotNull(message = "类型名称不能为空", groups = {AddGroup.class})
    @Size(max = 20, message = "类型名称必须20个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    private String objectTypeName;

    /**
     * 排除类型名称列表
     */
    @ApiModelProperty("排除类型名称列表")
    private String[] excludeObjectTypeNameArray;

    /**
     * 类型标识
     */
    @ApiModelProperty("类型标识")
    @NotNull(message = "类型标识不能为空", groups = {AddGroup.class})
    @Size(max = 20, message = "类型标识必须20个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    @Pattern(regexp = "^[a-zA-Z_]+$", message = "类型英文标识只能输入字母和下划线。", groups = {AddGroup.class, UpdateGroup.class})
    private String objectTypeLabel;

    /**
     * 类型编码
     */
    @ApiModelProperty("类型编码")
    @NotNull(message = "类型编码不能为空", groups = {AddGroup.class})
    //   @Pattern(regexp = "[A-Z0-9]{2}", message = "类型编码只能输入数字和大写字母，且不可以为00。", groups = {AddGroup.class, UpdateGroup.class})
//    @Pattern(regexp = "([A-Z0-9]{2})(^0{2})", message = "类型编码只能输入数字和大写字母，且不可以为00。", groups = {AddGroup.class, UpdateGroup.class})
    private String objectTypeCode;

    /**
     * 排除类型编码列表
     */
    @ApiModelProperty("排除类型编码列表")
    private String[] excludeObjectTypeCodeArray;

    /**
     * 类型编码
     */
    @ApiModelProperty("类型全码")
    @NotNull(message = "类型全码不能为空", groups = {AddGroup.class, UpdateGroup.class, DeleteGroup.class})
    //   @Pattern(regexp = "[A-Z0-9]{2}", message = "类型编码只能输入数字和大写字母，且不可以为00。", groups = {AddGroup.class, UpdateGroup.class})
//    @Pattern(regexp = "([A-Z0-9]{2})(^0{2})", message = "类型编码只能输入数字和大写字母，且不可以为00。", groups = {AddGroup.class, UpdateGroup.class})
    private String objectTypeFullCode;

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
     * 类型描述
     */
    @ApiModelProperty("类型描述")
    @Size(max = 50, message = "类型描述必须50个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    private String remark;

    /**
     * 空间维度
     */
    @ApiModelProperty("空间维度")
    private String spaceLevel;
}

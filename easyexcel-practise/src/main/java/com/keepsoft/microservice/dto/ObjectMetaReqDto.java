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
@ApiModel("对象元数据信息请求数据传输对象")
public class ObjectMetaReqDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -66879324475172690L;

    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    @NotNull(message = "对象主键ID不能为空", groups = {UpdateGroup.class, DeleteGroup.class})
    private Integer id;

    /**
     * 对象名称
     */
    @ApiModelProperty("对象名称")
    @NotNull(message = "对象名称不能为空", groups = {AddGroup.class})
    @Size(max = 50, message = "对象名称必须20个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    private String objectName;

    /**
     * 对象标识
     */
    @ApiModelProperty("对象标识")
    @NotNull(message = "对象标识不能为空", groups = {AddGroup.class})
    @Size(max = 20, message = "对象标识必须20个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    @Pattern(regexp = "^[a-zA-Z_]+$", message = "对象英文标识只能输入字母和下划线。", groups = {AddGroup.class, UpdateGroup.class})
    private String objectLabel;

    /**
     * 对象编码
     */
    @ApiModelProperty("对象编码")
    @NotNull(message = "对象编码不能为空", groups = {AddGroup.class})
    //   @Pattern(regexp = "[A-Z0-9]{2}", message = "对象编码只能输入数字和大写字母，且不可以为00。", groups = {AddGroup.class, UpdateGroup.class})
//    @Pattern(regexp = "([A-Z0-9]{2})(^0{2})", message = "对象编码只能输入数字和大写字母，且不可以为00。", groups = {AddGroup.class, UpdateGroup.class})
    private String objectCode;

    /**
     * 对象编码
     */
    @ApiModelProperty("对象全码")
//    @Pattern(regexp = "[A-Z0-9]{4}", message = "对象编码只能输入数字和大写字母，且不可以为00。", groups = {AddGroup.class, UpdateGroup.class})
    @NotNull(message = "对象全码不能为空", groups = {AddGroup.class, UpdateGroup.class, DeleteGroup.class})
    private String objectFullCode;

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
     * 对象描述
     */
    @ApiModelProperty("对象描述")
    @Size(max = 50, message = "对象描述必须50个字以内。", groups = {AddGroup.class, UpdateGroup.class})
    private String remark;

    /**
     * 对象所属类型编码
     */
    @ApiModelProperty("对象所属类型编码")
    private String objectTypeCode;

    /**
     * 对象所属类型全码
     */
    @ApiModelProperty("对象所属类型全码")
    @NotNull(message = "对象所属类型全码不能为空", groups = {AddGroup.class})
    private String objectTypeFullCode;

    /**
     * 对象所属类型名称
     */
    @ApiModelProperty("对象所属类型名称")
    private String objectTypeName;

    /**
     * 对象的空间维度等级，分为L1、L2、L3
     */
    @ApiModelProperty("对象的空间维度等级")
    private String spaceLevel;
}

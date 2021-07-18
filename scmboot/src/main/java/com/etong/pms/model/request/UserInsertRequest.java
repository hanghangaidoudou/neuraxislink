package com.etong.pms.model.request;

import com.etong.pms.model.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户信息插入实体
 * @author 王超华
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserInsertRequest", description = "用户信息插入实体")
public class UserInsertRequest extends Query {

  private static final long serialVersionUID = 5206377078931565894L;

  @Min(value = 1,message = "代码格式不正确")
  @Max(value = 99999999,message = "代码格式不正确")
  @ApiModelProperty(value = "代码")
  private Long userCode;

  @NotNull(message = "名称不能为空")
  @Length(min = 1,max = 255,message = "名称长度必须是1~255位")
  @ApiModelProperty(value = "名称")
  private String userName;


  @Length(min = 6,max = 16,message = "名称长度必须是6~16位")
  @ApiModelProperty(value = "密码")
  private String password;

  @NotNull(message = "角色不能为空")
  @ApiModelProperty(value = "角色")
  private String roles;

  @Pattern(regexp = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$", message = "手机号格式不正确")
  @ApiModelProperty(value = "手机号")
  private String tel;

  @Email(message = "邮箱格式不正确")
  @ApiModelProperty(value = "邮箱")
  private String email;

  @NotNull(message = "性别不能为空")
  @ApiModelProperty(value = "性别")
  private Integer sex;

  @NotNull(message = "部门不能为空")
  @ApiModelProperty(value = "部门Id")
  private Long deptId;

  @ApiModelProperty(value = "团队Id")
  private Long teamId;

  @NotNull(message = "在职状态不能为空")
  @ApiModelProperty(value = "在职状态")
  private String state;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @ApiModelProperty(value = "入职日期")
  private String entryDate;
}

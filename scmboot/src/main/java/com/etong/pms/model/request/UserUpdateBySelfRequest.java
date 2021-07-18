package com.etong.pms.model.request;

import com.etong.pms.model.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * 用户信息更新实体
 * @author 王超华
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserUpdateBySelfRequest", description = "用户信息更新实体")
public class UserUpdateBySelfRequest extends Query {

  private static final long serialVersionUID = 5206377078931565894L;

  @NotNull(message = "Id不能为空")
  @ApiModelProperty(value = "Id")
  private Long id;

  @NotNull(message = "名称不能为空")
  @Length(min = 1,max = 255,message = "名称长度必须是1~255位")
  @ApiModelProperty(value = "名称")
  private String userName;

  @Pattern(regexp = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$", message = "手机号格式不正确")
  @ApiModelProperty(value = "手机号")
  private String tel;

  @Email(message = "邮箱格式不正确")
  @ApiModelProperty(value = "邮箱")
  private String email;

  @NotNull(message = "性别不能为空")
  @ApiModelProperty(value = "性别")
  private Integer sex;

}

package com.etong.pms.model.request;

import com.etong.pms.model.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * 密码变更
 * @author  王超华
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PasswordUpdateRequest", description = "密码变更")
public class PasswordUpdateRequest extends Query {

  private static final long serialVersionUID = 329092295915213772L;

  @NotNull(message = "用户代码不能为空")
  @ApiModelProperty(value = "用户代码")
  private Long userCode;

  @NotNull(message = "原密码不能为空")
  @ApiModelProperty(value = "原密码")
  private String oldPassword;

  @Length(min = 6,max = 16,message = "名称长度必须是6~16位")
  @ApiModelProperty(value = "代码")
  private String newPassword;

  @ApiModelProperty(value = "类型（1:重置密码; 2:修改密码）")
  private Integer type;

}

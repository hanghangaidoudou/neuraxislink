package com.etong.pms.model.request;

import com.etong.pms.model.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * 团队信息插入实体
 * @author 王超华
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TeamInsertRequest", description = "团队信息插入实体")
public class TeamInsertRequest extends Query {

  private static final long serialVersionUID = 5206377078931565894L;

  @NotNull(message = "部门id不能为空")
  @ApiModelProperty(value = "部门id")
  private Long deptId;

  @NotNull(message = "代码不能为空")
  @Length(min = 1,max = 40,message = "代码长度必须是1~40位")
  @ApiModelProperty(value = "代码")
  private String teamCode;

  @NotNull(message = "名称不能为空")
  @Length(min = 1,max = 255,message = "名称长度必须是1~255位")
  @ApiModelProperty(value = "名称")
  private String teamName;

  @Length(max = 255,message = "描述长度必须是255位")
  @ApiModelProperty(value = "描述")
  private String teamDesc;

}

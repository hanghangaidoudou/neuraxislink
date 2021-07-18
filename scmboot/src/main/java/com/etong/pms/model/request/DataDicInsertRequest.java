package com.etong.pms.model.request;

import com.etong.pms.model.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * 数据字典插入实体
 * @author 王超华
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DataDicInsertRequest", description = "数据字典插入实体")
public class DataDicInsertRequest extends Query {

  private static final long serialVersionUID = 5206377078931565894L;
  @NotNull(message = "数据代码不能为空")
  @Length(min = 1,max = 40,message = "数据代码长度不正确")
  @ApiModelProperty(value = "数据代码")
  private String groupCode;

  @NotNull(message = "数据名称不能为空")
  @Length(min = 1,max = 255,message = "数据名称长度不正确")
  @ApiModelProperty(value = "数据名称")
  private String groupName;

  @Length(min = 0,max = 255,message = "数据描述长度不正确")
  @ApiModelProperty(value = "数据描述")
  private String groupDesc;

  @ApiModelProperty(value = "创建者")
  private Long creUserCode;

}

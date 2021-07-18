package com.etong.pms.model.request;

import com.etong.pms.model.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * 数据字典明细插入实体
 * @author 王超华
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DataDicItemInsertRequest", description = "数据字典明细插入实体")
public class DataDicItemInsertRequest extends Query {

  private static final long serialVersionUID = -4152736009127153960L;
  @NotNull(message = "数据字典id不能为空")
  @ApiModelProperty(value = "数据字典id")
  private Long dataDicId;

  @NotNull(message = "数据字典项目代码不能为空")
  @Length(min = 1,max = 80,message = "数据字典项目代码长度不正确")
  @ApiModelProperty(value = "数据字典项目代码")
  private String dataItemCode;

  @NotNull(message = "数据字典项目名称不能为空")
  @Length(min = 1,max = 255,message = "数据字典项目名称长度不正确")
  @ApiModelProperty(value = "数据字典项目名称")
  private String dataItemName;

  @Length(min = 0,max = 255,message = "数据字典项目描述长度不正确")
  @ApiModelProperty(value = "数据描述")
  private String dataItemDesc;

  @ApiModelProperty(value = "创建者")
  private Long creUserCode;

}

package com.etong.pms.model.request;

import com.etong.pms.model.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 数据字典明细插更新体
 * @author 王超华
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DataDicItemUpdateRequest", description = "数据字典明细更新实体")
public class DataDicItemUpdateRequest extends Query {

  private static final long serialVersionUID = -4152736009127153960L;
  @NotNull(message = "数据字典项目id不能为空")
  @ApiModelProperty(value = "数据字典项目id")
  private Long id;

  @NotNull(message = "数据字典项目名称不能为空")
  @Length(min = 1,max = 255,message = "数据字典项目名称长度不正确")
  @ApiModelProperty(value = "数据字典项目名称")
  private String dataItemName;

  @Length(min = 0,max = 255,message = "数据字典项目描述长度不正确")
  @ApiModelProperty(value = "数据描述")
  private String dataItemDesc;

  // @NotNull(message = "更新者不能为空")
  @ApiModelProperty(value = "更新者")
  private Long updUserCode;

  @NotNull(message = "版本号不能为空")
  @ApiModelProperty("版本号")
  private Integer version;
}

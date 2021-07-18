package com.etong.pms.model.request;

import com.etong.pms.model.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * 数据字典更新实体
 * @author 王超华
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DataDicUpdateRequest", description = "数据字典更新实体")
public class DataDicUpdateRequest extends Query {

  private static final long serialVersionUID = 8231989453388198578L;

  @NotNull(message = "数据字段id不能为空")
  @ApiModelProperty("id")
  private Long id;

  @NotNull(message = "数据名称不能为空")
  @Length(min = 1,max = 255,message = "数据名称长度不正确")
  @ApiModelProperty("数据名称")
  private String groupName;

  @Length(min = 0,max = 255,message = "数据描述长度不正确")
  @ApiModelProperty("数据描述")
  private String groupDesc;

  // @NotNull(message = "更新者不能为空")
  @ApiModelProperty("更新者")
  private Long updUserCode;

  @NotNull(message = "版本号不能为空")
  @ApiModelProperty("版本号")
  private Integer version;
}

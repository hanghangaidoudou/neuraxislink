package com.etong.pms.model.request;

import com.etong.pms.model.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据字典查询实体
 * @author 王超华
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DataDicRequest", description = "数据字典查询实体")
public class DataDicRequest extends PageQuery {

  private static final long serialVersionUID = 2902900869231326117L;

  @ApiModelProperty("数据代码")
  private String groupCode;

  @ApiModelProperty("数据名称")
  private String groupName;

}

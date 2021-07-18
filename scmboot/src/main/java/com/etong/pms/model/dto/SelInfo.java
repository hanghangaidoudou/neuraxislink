package com.etong.pms.model.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门（下拉框）类
 *
 * @author  王超华
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelInfo implements Serializable {
    @ApiModelProperty("值")
    public Long value;
    @ApiModelProperty("名称")
    public String label;
    @ApiModelProperty("下一级")
    public List<SelInfo> children;
}

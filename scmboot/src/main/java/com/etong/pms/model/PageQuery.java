package com.etong.pms.model;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页入参
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageQuery extends Query {

    private static final long serialVersionUID = -1961790338095894422L;

    @NotNull
    @ApiModelProperty(value = "当前页数")
    @Min(1)
    private int pageNum;

    @NotNull
    @ApiModelProperty(value = "每页条数")
    @Min(1)
    private int pageSize;
}

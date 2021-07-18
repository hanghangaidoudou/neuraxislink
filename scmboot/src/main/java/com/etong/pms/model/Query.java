package com.etong.pms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * 基础入参
 *
 */
@Data
public class Query implements Serializable {
    private static final long serialVersionUID = 446677491036426937L;
    @ApiModelProperty(hidden = true, value = "链路追踪 traceId")
    private String traceId;
}

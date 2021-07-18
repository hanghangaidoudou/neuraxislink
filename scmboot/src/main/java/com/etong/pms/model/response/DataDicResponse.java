package com.etong.pms.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 */
@Data
@ApiModel(value = "StopLogQueryResponse", description = "风险停复机查询返回实体")
public class DataDicResponse implements Serializable {

    private static final long serialVersionUID = 689375246951502936L;

    @ApiModelProperty("序号")
    private Long ventureId;

    @ApiModelProperty("用户号码")
    private String accNbr;

    @ApiModelProperty("用户id")
    private Long servId;

    @ApiModelProperty("工号")
    private Long staffId;

    @ApiModelProperty("业务类型")
    private String action;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("状态")
    private String state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("状态时间")
    private Date stateDate;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("处理结果")
    private String dealResult;

}

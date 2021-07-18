package com.etong.pms.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data //Get 和 Set get 方法
@AllArgsConstructor //固定
@NoArgsConstructor  //固定
public class PriceList {
    @ApiModelProperty("大类") //注释
    public String   classa;
    @ApiModelProperty("成本价")
    public BigDecimal cost;
    @ApiModelProperty("物料")
    public String mar;
    @ApiModelProperty("型号")
    public String    model;
    @ApiModelProperty("提货价")
    public BigDecimal   price;
    @ApiModelProperty("产品名称")
    public String    productname;
    @ApiModelProperty("系列")
    public String    serial;
}

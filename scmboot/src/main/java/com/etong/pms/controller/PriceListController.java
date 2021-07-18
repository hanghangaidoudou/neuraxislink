package com.etong.pms.controller;

import com.etong.pms.model.dto.PriceList;
import com.etong.pms.service.PriceListService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.etong.pms.model.Result;
import javax.annotation.Resource;
import java.util.List;

@Slf4j //日志
@RestController //必填项-公共类
@Api(tags = "产品信息") //接口组注释给Web提供API
@RequestMapping(value = "/ETong", produces = MediaType.APPLICATION_JSON_UTF8_VALUE) //接口组的根地址
public class PriceListController {

    @Resource //引用Service层
    private PriceListService priceListService;

    @GetMapping("/list") //接口地址
    @ApiOperation("产品列表") //接口的注释
    public  Result<PageInfo<PriceList>> getList(@ApiParam(name = "classa",value="类型",required = false) @RequestParam  String classa, @ApiParam(name = "serial",value="系列",required = false) @RequestParam  String serial, @ApiParam("产品名称") @RequestParam  String productname,        @ApiParam("页码") @RequestParam Integer pageNum,
                                                @ApiParam("每页条数") @RequestParam Integer pageSize) throws Exception { //参数的注释+参数
        return priceListService.getList( classa, serial, productname, pageNum, pageSize);
    }
}

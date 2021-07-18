package com.etong.pms.controller;

import com.etong.pms.domain.DataDicDetail;
import com.etong.pms.model.Result;
import com.etong.pms.domain.DataDic;
import com.etong.pms.model.dto.UserInfo;
import com.etong.pms.model.request.*;
import com.etong.pms.service.impl.DataDicServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

/**
 * @author 王超华
 */
@Slf4j
@RestController
@Api(tags = "数据字典")
@RequestMapping(value = "/ETong", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DataDicController {

    @Resource
    DataDicServiceImpl ddServiceImp;

    @GetMapping("/getDataDics")
    @ApiOperation("查询数据字典")
    public Result<PageInfo<DataDic>> getDataDics(@RequestParam String groupCode,
        @RequestParam String groupName,
        @RequestParam int pageNum, @RequestParam int pageSize) {
        log.info("查询数据字典 groupCode={}、groupName={}、pageNum={}、pageSize={}", groupCode, groupName,
            pageNum, pageSize);
        return ddServiceImp.getDataDics(groupCode,groupName,pageNum,pageSize);
    }

    @GetMapping("/getDataDic")
    @ApiOperation("查询数据字典详细数据")
    public Result<DataDicDetail> getDataDic(@ApiParam("数据字典id") @RequestParam Long dataDicId) {
        log.info("查询数据字典 request={0}", dataDicId);
        return ddServiceImp.getDataDicDetail(dataDicId);
    }

    @PostMapping("/addDataDic")
    @ApiOperation("插入数据字典")
    public Result addDataDic(@RequestBody @Valid DataDicInsertRequest request) {
        log.info("插入数据字典 request={}", request);
        return ddServiceImp.insertDataDic(request);
    }

    @GetMapping("/delDataDic")
    @ApiOperation("删除数据字典")
    public Result delDataDic(@ApiParam("数据字典id") @RequestParam Long dataDicId) {
        log.info("删除数据字典 dataDicId={}", dataDicId);
        return ddServiceImp.delDataDic(dataDicId);
    }

    @PostMapping("/updateDataDic")
    @ApiOperation("更新数据字典")
    public Result updateDataDic(@RequestBody @Valid DataDicUpdateRequest request) {
        log.info("更新数据字典 request={0}", request);
        return ddServiceImp.updateDataDic(request);
    }

    @PostMapping("/addDataDicItem")
    @ApiOperation("插入数据字典项目")
    public Result insertDataDicItem(@RequestBody @Valid DataDicItemInsertRequest request) {
        log.info("插入数据字典项目 request={0}", request);
        return ddServiceImp.insertDataDicItem(request);
    }

    @PostMapping("/updateDataDicItem")
    @ApiOperation("更新数据字典项目")
    public Result updateDataDicItem(@RequestBody @Valid DataDicItemUpdateRequest request) {
        log.info("更新数据字典项目 request={0}", request);
        return ddServiceImp.updateDataDicItem(request);
    }

    @GetMapping("/delDataDicItem")
    @ApiOperation("删除数据字典明细")
    public Result delDataDicItem(@ApiParam("数据字典明细id") @RequestParam Long dataDicItemId) {
        log.info("删除数据字典明细 dataDicItemId={}", dataDicItemId);
        return ddServiceImp.delDataDicItem(dataDicItemId);
    }
}

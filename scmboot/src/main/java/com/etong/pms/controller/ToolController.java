package com.etong.pms.controller;

import com.etong.pms.model.Result;
import com.etong.pms.model.dto.SelInfo;
import com.etong.pms.service.impl.ToolImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王超华
 */
@Slf4j
@RestController
@Api(tags = "工具类")
@RequestMapping(value = "/ETong", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ToolController {

    @Resource
    ToolImpl toolImp;

    @GetMapping("/getDataDicItem")
    @ApiOperation("根据数据项代码获取数据项明细")
    public Result<List<HashMap<Long,String>>> getDataDicItem(
        @ApiParam("数据项代码") @RequestParam String groupCode) {
        return toolImp.getDataDicItem(groupCode);
    }

    @GetMapping("/getDeptSelInfo")
    @ApiOperation("获取部门信息（级联下拉框）")
    public Result<List<SelInfo>> getSelInfo() {
        return toolImp.getDeptSel();
    }
}

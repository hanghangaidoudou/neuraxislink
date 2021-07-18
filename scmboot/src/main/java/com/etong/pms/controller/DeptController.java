package com.etong.pms.controller;

import com.etong.pms.domain.Dept;
import com.etong.pms.domain.DeptDetail;
import com.etong.pms.model.Result;
import com.etong.pms.model.request.*;
import com.etong.pms.service.DeptService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author 王超华
 */
@Slf4j
@RestController
@Api(tags = "部门操作")
@RequestMapping(value = "/ETong", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DeptController {

    @Resource
    DeptService deptService;

    @GetMapping("/getDeptById")
    @ApiOperation("根据ID获取部门信息")
    public Result<DeptDetail> getDeptById(@ApiParam("部门Id") @RequestParam Long deptId) {
        Result<DeptDetail> result = deptService.getDeptById(deptId);
        log.info("结果：{}" , result);
        return result;
    }

    @GetMapping("/getDeptInfos")
    @ApiOperation("查询部门信息")
    public Result<PageInfo<Dept>> getUserInfo(@ApiParam("部门代码") @RequestParam String code,
        @ApiParam("部门名称") @RequestParam String name,
        @ApiParam("页码") @RequestParam Integer pageNum,
        @ApiParam("每页条数") @RequestParam Integer pageSize) {
        return deptService.getDeptInfos(code, name, pageNum, pageSize);
    }

    @PostMapping("/insertDept")
    @ApiOperation("插入部门信息")
    public Result insertDept(@RequestBody @Valid DeptInsertRequest request) {
        return deptService.insertDept(request);
    }

    @GetMapping("/delDept")
    @ApiOperation("删除部门信息")
    public Result delDataDic(@ApiParam("部门id") @RequestParam Long deptId) {
        log.info("删除部门信息 deptId={}", deptId);
        return deptService.delDept(deptId);
    }

    @PostMapping("/updateDept")
    @ApiOperation("更新部门信息")
    public Result updateDept(@RequestBody @Valid DeptUpdateRequest request) {
        return deptService.updateDept(request);
    }

    @PostMapping("/insertTeam")
    @ApiOperation("插入团队信息")
    public Result insertTeam(@RequestBody @Valid TeamInsertRequest request) {
        return deptService.istTeam(request);
    }

    @PostMapping("/updateTeam")
    @ApiOperation("更新团队信息")
    public Result updateTeam(@RequestBody @Valid TeamUpdateRequest request) {
        return deptService.updTeam(request);
    }

    @GetMapping("/delTeam")
    @ApiOperation("删除团队信息")
    public Result delTeamDic(@ApiParam("团队id") @RequestParam Long teamId) {
        log.info("删除团队信息 teamId={}", teamId);
        return deptService.delTeam(teamId);
    }

}

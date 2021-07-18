package com.etong.pms.controller;

import com.etong.pms.model.Result;
import com.etong.pms.model.request.UserInsertRequest;
import com.etong.pms.model.request.UserUpdateBySelfRequest;
import com.etong.pms.model.request.UserUpdateRequest;
import com.etong.pms.model.request.PasswordUpdateRequest;
import com.etong.pms.service.impl.UserServiceImpl;
import com.etong.pms.model.dto.UserInfo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author 王超华
 */
@Slf4j
@RestController
@Api(tags = "用户操作")
@RequestMapping(value = "/ETong", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    @Resource
    UserServiceImpl userServiceImp;

    @GetMapping("/getUserInfoById")
    @ApiOperation("根据ID用户信息")
    public Result<UserInfo> getUserInfoById(@ApiParam("用户Id") @RequestParam Long id) {
        return userServiceImp.getUserInfoById(id);
    }


    @GetMapping("/getUserInfos")
    @ApiOperation("查询用户信息")
    public Result<PageInfo<UserInfo>> getUserInfo(@ApiParam("用户代码") @RequestParam Long userCode,
        @ApiParam("用户名称") @RequestParam String userName,
        @ApiParam("部门Id") @RequestParam Long deptId,
        @ApiParam("团队Id") @RequestParam Long teamId,
        @ApiParam("在职状态") @RequestParam Long state,
        @ApiParam("页码") @RequestParam Integer pageNum,
        @ApiParam("每页条数") @RequestParam Integer pageSize) {
        return userServiceImp.getUserInfos(userCode,userName,deptId,teamId,state,pageNum,pageSize);
    }
    @PostMapping("/insertUser")
    @ApiOperation("插入用户信息")
    public Result insertUser(@RequestBody @Valid UserInsertRequest request) {
        return userServiceImp.insertUser(request);
    }

    @PostMapping("/updateUser")
    @ApiOperation("更新用户信息")
    public Result updateUser(@RequestBody @Valid UserUpdateRequest request) {
        return userServiceImp.updateUser(request);
    }
    @PostMapping("/updatePassword")
    @ApiOperation("修改用户密码")
    public Result updatePassword(@RequestBody @Valid PasswordUpdateRequest request) {
        return userServiceImp.updatePassword(request);
    }

    @PostMapping("/updUserInfoBySelf")
    @ApiOperation("用户自己更新信息")
    public Result updUserInfoBySelf(@RequestBody @Valid UserUpdateBySelfRequest request) {
        return userServiceImp.updUserBySelf(request);
    }
}

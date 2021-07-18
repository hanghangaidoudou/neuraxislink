package com.etong.pms.controller;

import com.etong.pms.service.impl.LoginServiceImpl;
import com.etong.pms.model.Result;
import com.etong.pms.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王超华
 */
@Slf4j
@RestController
@Api(tags = "登录")
@RequestMapping(value = "/ETong", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginController {

    @Resource
    LoginServiceImpl lServiceImp;

    @PostMapping("/login")
    @ApiOperation("登录系统")
    public Result login(@ApiParam("用户代码") @RequestParam Long userCode,
        @ApiParam("密码") @RequestParam String password) {
        log.info("登录系统，参数={}", userCode);
        return lServiceImp.login(userCode, password);
    }

    @PostMapping("/logout")
    @ApiOperation("退出系统")
    public Result out() {
        return lServiceImp.out();
    }

    @GetMapping("/info")
    @ApiOperation("获取用户信息")
    public Result info(@ApiParam("用户代码") @RequestParam String token) {
        log.info("获取用户信息，参数={}", token);
        String userCode = JwtUtil.getCode(token);
        return lServiceImp.getUserInfo(Long.valueOf(userCode));
    }
}

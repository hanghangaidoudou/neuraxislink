package com.etong.pms.service.impl;

import com.etong.pms.base.exception.ResultStatusEnums;
import com.etong.pms.constant.Constant;
import com.etong.pms.model.Result;
import com.etong.pms.model.dto.UserInfo;
import com.etong.pms.repository.UserMapper;
import com.etong.pms.service.LoginService;
import com.etong.pms.utils.JwtUtil;
import com.etong.pms.utils.MD5Util;
import com.etong.pms.utils.ResultFactory;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统登录、退出实现
 *
 * @author 王超华
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {

    @Resource
    public UserMapper userMapper;

    /**
     * 登录系统
     */
    @Override
    public Result login(Long userCode, String password) {
        String newPassword = MD5Util.getMD5(password);
        Integer count = userMapper.checkUser(userCode, newPassword);
        if (count == 0) {
            // 登录失败，用户名或密码错误！
            return ResultFactory.newError(ResultStatusEnums.LOGIN_ERROR);
        }
        // 生成Token
        String token = JwtUtil
            .createJWT(String.valueOf(userCode), Constant.JJWT_ISSUER, Constant.JJWT_SUBJECT,
                Constant.JJWT_TTLMILLIS);
        log.info("token="+token);
        return ResultFactory.of(token);
    }

    /**
     * 退出系统
     */
    @Override
    public Result out() {
        return ResultFactory.ok();
    }

    /**
     * 获取用户信息
     */
    @Override
    public Result<UserInfo> getUserInfo(Long userCode) {
        UserInfo userInfo = userMapper.getUserInfo(userCode);
        return ResultFactory.of(userInfo);
    }
}

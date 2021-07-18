package com.etong.pms.service;

import com.etong.pms.model.Result;
import com.etong.pms.model.dto.UserInfo;

/**
 * 系统登录、退出接口
 *
 * @author 王超华
 */
public interface LoginService {

  /**
   * 登录系统
   * @param userCode 用户代码
   * @param password 密码
   * @return
   */
  Result login(Long userCode,String password);

  /**
   * 退出系统
   *
   * @return
   */
  Result out();
  /**
   * 获取用户信息
   *
   * @param userCode 用户代码
   * @return 用户信息
   */
  Result<UserInfo> getUserInfo(Long userCode);
}

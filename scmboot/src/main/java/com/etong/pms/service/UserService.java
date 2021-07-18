package com.etong.pms.service;

import com.etong.pms.model.dto.UserInfo;
import com.etong.pms.model.Result;
import com.etong.pms.model.request.PasswordUpdateRequest;
import com.etong.pms.model.request.UserInsertRequest;
import com.etong.pms.model.request.UserUpdateBySelfRequest;
import com.etong.pms.model.request.UserUpdateRequest;
import com.github.pagehelper.PageInfo;

/**
 * 用户 业务类接口
 *
 * @author 王超华
 */
public interface UserService {

    /**
     * 通过Id获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    Result<UserInfo> getUserInfoById(Long id);

    /**
     * 查询用户信息
     *
     * @param userCode 用户代码
     * @param userName 用户名称
     * @param deptId 部门Id
     * @param teamId 团队Id
     * @param state 状态
     * @return 用户列表
     */
    Result<PageInfo<UserInfo>> getUserInfos(Long userCode, String userName, Long deptId, Long teamId,
        Long state,Integer pageNum ,Integer pageSize);

    /**
     * 追加新用户
     * @param request 参数
     * @return 结果
     */
    Result insertUser(UserInsertRequest request);

    /**
     * 更新用户信息
     * @param request 参数
     * @return 结果
     */
    Result updateUser(UserUpdateRequest request);

    /**
     * 修改密码
     *
     * @param request
     * @return
     */
    Result updatePassword(PasswordUpdateRequest request);

    /**
     * 用户自己更新信息
     *
     * @param request
     * @return
     */
    Result updUserBySelf(UserUpdateBySelfRequest request);
}

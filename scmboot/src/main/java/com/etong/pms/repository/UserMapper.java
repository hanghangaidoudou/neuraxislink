package com.etong.pms.repository;

import com.etong.pms.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.etong.pms.model.dto.UserInfo;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    /**
     * 校验登录用户
     *
     * @param userCode 用户代码
     * @param password 密码
     * @return 结果
     */
    Integer checkUser(@Param("userCode") Long userCode,
        @Param("password") String password);

    /**
     * 获取用户信息
     *
     * @param userCode 用户代码
     * @return 用户信息
     */
    UserInfo getUserInfo(@Param("userCode") Long userCode);

    /**
     * 检查用户代码是否存在
     *
     * @param userCode 用户代码
     * @return 条数
     */
    Integer checkUserCode(@Param("userCode") Long userCode);

    /**
     * 获取用户信息
     *
     * @param id 用户Id
     * @return 用户信息
     */
    UserInfo getUserInfoById(@Param("id") Long id);

    /**
     * 获取用户信息
     *
     * @param userCode 用户代码
     * @param userName 用户名称
     * @param deptId 部门代码
     * @param teamId 团队代码
     * @param state 在职状态
     * @return 用户列表
     */
    List<UserInfo> getUserInfos(@Param("userCode") Long userCode,
        @Param("userName") String userName, @Param("deptId") Long deptId,
        @Param("teamId") Long teamId, @Param("state") Long state);

    /**
     * 插入用户数据
     *
     * @param user 用户对象
     * @return 结果
     */
    Integer istUser(User user);

    /**
     * 更新用户数据
     *
     * @param user 用户对象
     * @return 结果
     */
    Integer updUser(User user);

    /**
     * 重置密码
     *
     * @param userCode 用户代码
     * @param password 密码
     * @return 结果
     */
    Integer resetPassword(@Param("userCode") Long userCode, @Param("password") String password);

    /**
     * 检查旧密码
     *
     * @param userCode 用户代码
     * @param password 旧密码密码
     */
    Integer checkOldPassword(@Param("userCode") Long userCode, @Param("password") String password);

    /**
     * 用户信息
     * @param user
     * @return
     */
    Integer updUserBySelf(User user);

}

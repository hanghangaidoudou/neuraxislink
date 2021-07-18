package com.etong.pms.service.impl;

import com.etong.pms.base.exception.CommonException;
import com.etong.pms.base.exception.ResultStatusEnums;
import com.etong.pms.constant.GlobalConstant.Number;
import com.etong.pms.domain.User;
import com.etong.pms.model.Result;
import com.etong.pms.model.request.PasswordUpdateRequest;
import com.etong.pms.model.request.UserInsertRequest;
import com.etong.pms.model.request.UserUpdateBySelfRequest;
import com.etong.pms.model.request.UserUpdateRequest;
import com.etong.pms.repository.UserMapper;
import com.etong.pms.service.UserService;
import com.etong.pms.utils.MD5Util;
import com.etong.pms.utils.ResultFactory;
import com.etong.pms.model.dto.UserInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import java.util.List;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户 业务类实现
 *
 * @author 王超华
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    public UserMapper userMapper;

    /**
     * 根据用户id 获取用户信息
     *
     * @param id 用户Id
     * @return 用户信息
     */
    @Override
    public Result<UserInfo> getUserInfoById(Long id) {
        UserInfo userInfo = userMapper.getUserInfoById(id);
        return ResultFactory.of(userInfo);
    }

    /**
     * 查询用户信息
     *
     * @param userCode 用户代码
     * @param userName 用户名称
     * @param deptId 部门Id
     * @param teamId 团队Id
     * @param state 在职状态
     * @return 用户列表
     */
    @Override
    public Result<PageInfo<UserInfo>> getUserInfos(Long userCode, String userName, Long deptId,
        Long teamId, Long state,Integer pageNum ,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<UserInfo> userInfos = userMapper.getUserInfos(userCode,userName,deptId,teamId,state);
        PageInfo<UserInfo> pageInfos = new PageInfo<>(userInfos);
        return ResultFactory.of(pageInfos);
    }

    /**
     * 追加新用户
     *
     * @param request 参数
     * @return 结果
     */
    @Override
    public Result insertUser(UserInsertRequest request) {
        // 验证用户代码是否已经存在
        Long userCode = request.getUserCode();
        Integer count = userMapper.checkUserCode(userCode);
        if(count == Number.ONE){
            // 用户代码在系统中存在
            throw new CommonException(ResultStatusEnums.USERCODE_EXIST);
        }
        User user = this.getIstUser(request);

        int iCount = userMapper.istUser(user);
        Preconditions.checkArgument(iCount == 1, "追加失败！");
        return ResultFactory.ok();
    }

    /**
     * 更新用户
     *
     * @param request 参数
     * @return 结果
     */
    @Override
    public Result updateUser(UserUpdateRequest request) {
        User user = this.getUpdUser(request);
        int uCount = userMapper.updUser(user);
        Preconditions.checkArgument(uCount == 1, "更新失败！");
        return ResultFactory.ok();
    }

    /**
     * 修改用户密码
     * @param request
     * @return
     */
    @Override
    public Result updatePassword(PasswordUpdateRequest request) {
        Long userCode = request.getUserCode();
        String oldPassword = request.getOldPassword();
        String newPassword = request.getNewPassword();
        // 类型（1:重置密码; 2:修改密码） 验证旧密码是否正确
        if(Number.TWO == request.getType()){
            Integer count = userMapper.checkOldPassword(userCode, MD5Util.getMD5(oldPassword));
            if(count != 1 ){
                throw new CommonException(ResultStatusEnums.OLDPASSWORD_ERROR);
            }
        }
        // 设置新密码
        Integer uCount = userMapper.resetPassword( userCode,  MD5Util.getMD5(newPassword));
        Preconditions.checkArgument(uCount == 1, "更新失败！");
        return ResultFactory.ok();
    }

    /**
     * 用户自己更新信息
     */
    @Override
    public Result updUserBySelf(UserUpdateBySelfRequest request) {
        User user = new User();
        user.setId(request.getId());
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setTel(request.getTel());
        user.setSex(request.getSex());
        Integer uCount = userMapper.updUserBySelf(user);
        Preconditions.checkArgument(uCount == 1, "更新失败！");
        return ResultFactory.ok();
    }

    /**
     * 构建User 插入对象
     * @param req
     * @return
     */
    private User getIstUser(UserInsertRequest req){
        User user = new User();
        user.setUserCode(req.getUserCode());
        user.setUserName(req.getUserName());
        // 密码加密
        user.setPassword(MD5Util.getMD5(req.getPassword()));
        user.setRoles(req.getRoles());
        user.setTel(req.getTel());
        user.setEmail(req.getEmail());
        user.setSex(req.getSex());
        user.setDeptId(req.getDeptId());
        user.setTeamId(req.getTeamId());
        user.setState(req.getState());
        user.setEntryDate(req.getEntryDate());
        return user;
    }
    /**
     * 构建User 更新对象
     * @param req
     * @return
     */
    private User getUpdUser(UserUpdateRequest req){
        User user = new User();
        user.setId(req.getId());
        user.setUserName(req.getUserName());
        user.setRoles(req.getRoles());
        user.setTel(req.getTel());
        user.setEmail(req.getEmail());
        user.setSex(req.getSex());
        user.setDeptId(req.getDeptId());
        user.setTeamId(req.getTeamId());
        user.setState(req.getState());
        user.setEntryDate(req.getEntryDate());
        return user;
    }
}

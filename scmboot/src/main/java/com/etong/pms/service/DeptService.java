package com.etong.pms.service;

import com.etong.pms.domain.*;
import com.etong.pms.model.Result;
import com.etong.pms.model.request.*;
import com.github.pagehelper.PageInfo;

/**
 * 用户 业务类接口
 *
 * @author 王超华
 */
public interface DeptService {

    /**
     * 通过Id获取部门信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    Result<DeptDetail> getDeptById(Long deptId);

    /**
     * 查询部门信息
     * @param code
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result<PageInfo<Dept>> getDeptInfos(String code, String name,Integer pageNum, Integer pageSize);

    /**
     * 追加部门信息
     * @param request 参数
     * @return 结果
     */
    Result insertDept(DeptInsertRequest request);

    /**
     * 更新部门信息
     * @param request 参数
     * @return 结果
     */
    Result updateDept(DeptUpdateRequest request);


    /**
     * 删除部门信息
     * @param deptId 参数
     * @return 结果
     */
    Result delDept(Long deptId);


    /**
     * 插入团队信息
     *
     * @param request 团队信息
     * @return
     */
    Result istTeam(TeamInsertRequest request);

    /**
     * 插入团队信息
     *
     * @param request 团队信息
     * @return
     */
    Result updTeam(TeamUpdateRequest request);


    /**
     * 删除团队信息
     *
     * @param teamId 团队ID
     * @return
     */
    Result delTeam(Long teamId);
}

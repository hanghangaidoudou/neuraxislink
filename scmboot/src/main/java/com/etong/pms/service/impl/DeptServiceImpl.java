package com.etong.pms.service.impl;

import com.etong.pms.base.exception.ResultStatusEnums;
import com.etong.pms.domain.*;
import com.etong.pms.model.Result;
import com.etong.pms.model.request.*;
import com.etong.pms.repository.DeptMapper;
import com.etong.pms.service.DeptService;
import com.etong.pms.utils.PreCheck;
import com.etong.pms.utils.ResultFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 部门 业务类实现
 *
 * @author 王超华
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeptServiceImpl implements DeptService {

    @Resource
    public DeptMapper deptMapper;

    /**
     * 根据用户id 获取部门信息
     *
     * @param deptId 部门Id
     * @return 部门信息
     */
    @Override
    public Result<DeptDetail> getDeptById(Long deptId) {
        DeptDetail deptDetail = deptMapper.getDeptDetail(deptId);
        PreCheck.checkNotNull(deptDetail, ResultStatusEnums.DEPTINFO_NULL);
        // 查询团队信息
        List<Team> teams = deptMapper.getTeam(deptId);
        deptDetail.setTeams(teams);
        return ResultFactory.of(deptDetail);
    }

    /**
     * 查询部门信息
     */
    @Override
    public Result<PageInfo<Dept>> getDeptInfos(String code, String name, Integer pageNum,
        Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<Dept> deptInfos = deptMapper.getDept(null, code, name);
        PageInfo<Dept> pageInfos = new PageInfo<>(deptInfos);
        return ResultFactory.of(pageInfos);
    }

    /**
     * 追加新用户
     *
     * @param request 参数
     * @return 结果
     */
    @Override
    public Result insertDept(DeptInsertRequest request) {
        Dept dept = new Dept();
        dept.code = request.getCode();
        dept.name = request.getName();
        dept.desc = request.getDesc();
        Integer iCount = deptMapper.istDept(dept);
        Preconditions.checkArgument(iCount == 1, "追加部门信息失败！");
        return ResultFactory.ok();
    }

    /**
     * 更新用户信息
     *
     * @param request 参数
     * @return 结果
     */
    @Override
    public Result updateDept(DeptUpdateRequest request) {
        Dept dept = new Dept();
        dept.setId(request.getId());
        dept.setCode(request.getCode());
        dept.setName(request.getName());
        dept.setDesc(request.getDesc());
        Integer iCount = deptMapper.updDept(dept);
        Preconditions.checkArgument(iCount == 1, "更新部门信息失败！");
        return ResultFactory.ok();
    }

    /**
     * 删除用户信息
     *
     * @param deptId 参数
     * @return 结果
     */
    @Override
    public Result delDept(Long deptId) {
        List<Team> teams = deptMapper.getTeam(deptId);
        // 删除部门下团队信息
        if (teams != null && !teams.isEmpty()) {
            for (Team team : teams) {
                Integer dCount = deptMapper.delTeam(team.id);
                Preconditions.checkArgument(dCount == 1, "删除团队信息失败！");
            }
        }
        //删除部门信息
        Integer delCount = deptMapper.delDept(deptId);
        Preconditions.checkArgument(delCount == 1, "删除部门信息失败！");
        return ResultFactory.ok();
    }

    /**
     * 插入团队信息
     */
    @Override
    public Result istTeam(TeamInsertRequest request) {
        Team team = new Team();
        team.setCode(request.getTeamCode());
        team.setName(request.getTeamName());
        team.setDesc(request.getTeamDesc());
        team.setDeptId(request.getDeptId());
        Integer iCount = deptMapper.istTeam(team);
        Preconditions.checkArgument(iCount == 1, "插入团队信息失败！");
        return ResultFactory.ok();
    }

    /**
     * 插入团队信息
     */
    @Override
    public Result updTeam(TeamUpdateRequest request) {
        Team team = new Team();
        team.setId(request.getId());
        team.setCode(request.getTeamCode());
        team.setName(request.getTeamName());
        team.setDesc(request.getTeamDesc());
        Integer uCount = deptMapper.updTeam(team);
        Preconditions.checkArgument(uCount == 1, "更新团队信息失败！");
        return ResultFactory.ok();
    }

    /**
     * 删除团队信息
     */
    @Override
    public Result delTeam(Long teamId) {
        Integer dCount = deptMapper.delTeam(teamId);
        Preconditions.checkArgument(dCount == 1, "删除团队信息失败！");
        return ResultFactory.ok();
    }
}

package com.etong.pms.repository;

import com.etong.pms.domain.Dept;
import com.etong.pms.domain.DeptDetail;
import com.etong.pms.domain.Team;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DeptMapper {

    /**
     * 取部门信息
     * @param id id
     * @param code 代码
     * @param name 名称
     * @return 部门信息
     */
    List<Dept> getDept(@Param("id") Long id, @Param("code") String code,
        @Param("name") String name);

    /**
     * 获取部门信息
     * @param deptId 部门ID
     * @return
     */
    DeptDetail getDeptDetail(@Param("deptId") Long deptId);

    /**
     * 取团队信息
     *
     * @param id 部门Id
     * @return 结果
     */
    List<Team> getTeam(@Param("id") Long id);

    /**
     * 插入部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    Integer istDept(Dept dept);

    /**
     * 更新部门信息
     * @param dept 部门信息
     * @return 结果
     */
    Integer updDept(Dept dept);

    /**
     * 删除部门信息
     * @param deptId 部门Id
     * @return 结果
     */
    Integer delDept(Long deptId);


    /**
     * 插入团队信息
     * @param team 团队信息
     * @return 结果
     */
    Integer istTeam(Team team);

    /**
     * 更新团队信息
     * @param team 团队信息
     * @return 结果
     */
    Integer updTeam(Team team);

    /**
     * 删除团队信息
     * @param teamId 团队ID
     * @return 结果
     */
    Integer delTeam(@Param("teamId") Long teamId);
}

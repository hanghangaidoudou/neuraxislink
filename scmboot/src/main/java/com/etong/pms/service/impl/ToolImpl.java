package com.etong.pms.service.impl;

import com.etong.pms.domain.Dept;
import com.etong.pms.domain.Team;
import com.etong.pms.model.Result;
import com.etong.pms.model.dto.SelInfo;
import com.etong.pms.repository.DeptMapper;
import com.etong.pms.repository.ToolMapper;
import com.etong.pms.service.ToolService;
import com.etong.pms.utils.ResultFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 工具类
 *
 * @author 王超华
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ToolImpl implements ToolService {

    @Resource
    public ToolMapper toolMapper;
    @Resource
    public DeptMapper deptMapper;

    /**
     * 根据数据项代码查询数据项明细
     */
    @Override
    public Result<List<HashMap<Long,String>>> getDataDicItem(String groupCode) {
        List<HashMap<Long,String>> list = toolMapper.getDataDicItem(groupCode);
        return ResultFactory.of(list);
    }

    /**
     * 获取部门下拉框的值
     *
     * @return 部门下拉框信息
     */
    @Override
    public Result<List<SelInfo>> getDeptSel() {
        List<SelInfo> SelInfos = new ArrayList<SelInfo>();
        //  查询部门信息
        List<Dept> deptList = deptMapper.getDept(null,null,null);
        for (Dept dept: deptList) {
            SelInfo deptInfo = this.getSelInfo(dept.id,dept.name);
            //  查询部门下的团队信息
            List<Team> teamList = deptMapper.getTeam(dept.id);
            if(teamList!=null && !teamList.isEmpty()){
                deptInfo.children = new ArrayList<SelInfo>();
                for (Team team: teamList) {
                    SelInfo teamInfo = this.getSelInfo(team.id,team.name);
                    deptInfo.children.add(teamInfo);
                }
            }
            SelInfos.add(deptInfo);
        }
        return ResultFactory.of(SelInfos);
    }

    /**
     * 构建 SelInfo 对象
     * @param value 值
     * @param label 名称
     * @return
     */
    private SelInfo getSelInfo(Long value , String label){
        SelInfo SelInfo = new SelInfo();
        SelInfo.value = value;
        SelInfo.label = label;
        return SelInfo;
    }
}

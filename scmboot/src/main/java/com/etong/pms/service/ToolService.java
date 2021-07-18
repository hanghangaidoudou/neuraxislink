package com.etong.pms.service;

import com.etong.pms.model.Result;
import com.etong.pms.model.dto.SelInfo;
import java.util.HashMap;
import java.util.List;

/**
 * 工具类接口
 *
 * @author 王超华
 */
public interface ToolService {

    /**
     * 根据数据项代码查询数据项明细
     *
     * @param groupCode
     * @return
     */
    Result<List<HashMap<Long,String>>> getDataDicItem(String groupCode);

    /**
     * 获取部门下拉框的值
     *
     * @return
     */
    Result<List<SelInfo>> getDeptSel();
}

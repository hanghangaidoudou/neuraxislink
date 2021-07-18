package com.etong.pms.repository;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ToolMapper {

    /**
     *根据 获取字典明细
     * @param groupCode 数据字典代码
     * @return
     */
    List<HashMap<Long,String>> getDataDicItem(@Param("groupCode") String groupCode);


}

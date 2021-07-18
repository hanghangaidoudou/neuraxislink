package com.etong.pms.repository;

import com.etong.pms.domain.DataDic;
import com.etong.pms.domain.DataDicDetail;
import com.etong.pms.domain.DataDicItem;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DataDicMapper {

    /**
     * 获取数据字典列表
     *
     * @param groupCode 数据代码
     * @param groupName 数据名称
     * @return 结果
     */
    List<DataDic> getDataDics(@Param("groupCode") String groupCode,
        @Param("groupName") String groupName);

    /**
     * 获取数据字典详细
     *
     * @param id id
     * @return 结果
     */
    DataDicDetail getDataDicDetail(@Param("id") Long id);

    /**
     * 插入数据字典
     *
     * @param dataDic DataDic
     * @return 更新条数
     */
    Integer insertDataDic(DataDic dataDic);

    /**
     * 通过id更新数据字典
     *
     * @param dataDic DataDic
     * @return 更新条数
     */
    Integer updateDataDic(DataDic dataDic);

    /**
     * 根据Id删除数据字典
     *
     * @param dataDicId dataDicId
     * @return 更新条数
     */
    Integer delDataDicById(Long dataDicId);
    
    /**
     * 根据数据字典Id获取数据字典明细
     *
     * @param dataDicId 数据字典项目Id
     * @param dataItemCode 数据项目明细代码
     * @return 结果
     */
    List<DataDicItem> getDataDicItems(@Param("dataDicId") Long dataDicId,
        @Param("dataItemCode") String dataItemCode);

    /**
     * 插入数据字典明细
     *
     * @param dataDicItem DataDicItem
     * @return 更新条数
     */
    Integer insertDataDicItem(DataDicItem dataDicItem);

    /**
     * 更新数据字典明细
     *
     * @param dataDicItem dataDicItem
     * @return 更新条数
     */
    Integer updateDataDicItem(DataDicItem dataDicItem);

    /**
     * 根据Id删除数据字典明细
     *
     * @param dataItemDicId dataItemDicId
     * @return 更新条数
     */
    Integer delDataDicItemById(Long dataItemDicId);
}

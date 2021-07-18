package com.etong.pms.service;

import com.etong.pms.domain.DataDicDetail;
import com.etong.pms.model.Result;
import com.etong.pms.domain.DataDic;
import com.etong.pms.model.request.*;
import com.github.pagehelper.PageInfo;

/**
 * 数据字典业务类接口
 *
 * @author 王超华
 */
public interface DataDicService {

  /**
   * 获取 数据字典信息
   * @param groupCode 代码
   * @param groupName 名称
   * @param pageNum 页面
   * @param pageSize 条数/页
   * @return
   */
  Result<PageInfo<DataDic>> getDataDics(String groupCode , String groupName,Integer pageNum,Integer pageSize);

  /**
   * 根据数据字典Id获取数据字典明细
   *
   * @param dataDicId 参数
   * @return 结果
   */
  Result<DataDicDetail> getDataDicDetail(Long dataDicId);

  /**
   * 插入数据字典
   *
   * @param request 参数
   * @return 结果
   */
  Result insertDataDic(DataDicInsertRequest request);

  /**
   * 删除数据字典
   *
   * @param dataDicId 数据字典Id
   * @return 结果
   */
  Result delDataDic(Long dataDicId);

  /**
   * 通过id更新数据字典
   *
   * @param request 参数
   * @return 结果
   */
  Result updateDataDic(DataDicUpdateRequest request);

  /**
   * 插入数据字典明细
   *
   * @param request 参数
   * @return 结果
   */
  Result insertDataDicItem(DataDicItemInsertRequest request);

  /**
   * 更新数据字典明细
   *
   * @param request 参数
   * @return 结果
   */
  Result updateDataDicItem(DataDicItemUpdateRequest request);

  /**
   * 删除数据字典明细
   *
   * @param dataDicItemId 数据字典明细Id
   * @return 结果
   */
  Result delDataDicItem(Long dataDicItemId);

}

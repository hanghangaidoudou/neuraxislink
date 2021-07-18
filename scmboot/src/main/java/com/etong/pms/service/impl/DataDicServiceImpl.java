package com.etong.pms.service.impl;

import com.etong.pms.base.exception.CommonException;
import com.etong.pms.base.exception.ResultStatusEnums;
import com.etong.pms.domain.DataDicItem;
import com.etong.pms.model.Result;
import com.etong.pms.domain.DataDic;
import com.etong.pms.domain.DataDicDetail;
import com.etong.pms.model.request.*;
import com.etong.pms.repository.DataDicMapper;
import com.etong.pms.service.DataDicService;
import com.etong.pms.utils.ResultFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户 业务类实现
 *
 * @author 王超华
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DataDicServiceImpl implements DataDicService {

    @Resource
    public DataDicMapper ddMapper;

    /**
     * 获取 数据字典信息
     *
     * @param groupCode 代码
     * @param groupName 名称
     * @param pageNum 页面
     * @param pageSize 条数/页
     */
    @Override
    public Result<PageInfo<DataDic>> getDataDics(String groupCode, String groupName, Integer pageNum,
        Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<DataDic> list = ddMapper.getDataDics(groupCode, groupName);
        PageInfo<DataDic> pageInfos = new PageInfo<>(list);
        return ResultFactory.of(pageInfos);
    }

    /**
     * 插入数据字典
     *
     * @param request 传入参数
     * @return 结果
     */
    @Override
    public Result insertDataDic(DataDicInsertRequest request) {
        List<DataDic> list = ddMapper.getDataDics(request.getGroupCode(), null);
        if (!list.isEmpty()) {
            throw new CommonException(ResultStatusEnums.DATADICCODE_ISEXIST, "数据代码已经存在！");
        }
        DataDic dataDic = this.getDataDicForInert(request);
        int iCount = ddMapper.insertDataDic(dataDic);
        Preconditions.checkArgument(iCount == 1, "添加失败！");
        return ResultFactory.ok();
    }

    /**
     * 删除数据字典
     *
     * @param dataDicId 数据字典Id
     * @return 结果
     */
    @Override
    public Result delDataDic(Long dataDicId) {

        // 查询明细
        List<DataDicItem> items = ddMapper.getDataDicItems(dataDicId, null);
        if (items != null && !items.isEmpty()) {
            for (DataDicItem item : items) {
                //删除明细
                Integer dCount = ddMapper.delDataDicItemById(item.id);
                Preconditions.checkArgument(dCount == 1, "删除失败！");
            }
        }
        // 删除数据项
        Integer dCount = ddMapper.delDataDicById(dataDicId);
        Preconditions.checkArgument(dCount == 1, "删除失败！");
        return ResultFactory.ok();
    }

    /**
     * 通过id更新数据字典
     *
     * @param request 传入参数
     * @return 结果
     */
    @Override
    public Result updateDataDic(DataDicUpdateRequest request) {
        DataDic dataDic = this.getDataDicForUpdate(request);
        int uCount = ddMapper.updateDataDic(dataDic);
        Preconditions.checkArgument(uCount == 1, "更新失败！");
        return ResultFactory.ok();
    }

    /**
     * 根据数据字典Id获取数据字典详细数据
     *
     * @param dataDicId 参数
     * @return 结果
     */
    @Override
    public Result<DataDicDetail> getDataDicDetail(Long dataDicId) {
        DataDicDetail dataDicDetail = ddMapper.getDataDicDetail(dataDicId);
        if (dataDicDetail != null) {
            dataDicDetail.setItems(ddMapper.getDataDicItems(dataDicId, null));
        }
        return ResultFactory.of(dataDicDetail);
    }

    /**
     * 插入数据字典明细
     *
     * @param request 参数
     * @return 结果
     */
    @Override
    public Result insertDataDicItem(DataDicItemInsertRequest request) {
        List<DataDicItem> list = ddMapper.getDataDicItems(null, request.getDataItemCode());
        if (!list.isEmpty()) {
            throw new CommonException(ResultStatusEnums.DATADICITEMCODE_ISEXIST, "数据项目代码已经存在！");
        }
        DataDicItem ddItem = this.getDataDicItemForInsert(request);
        int iCount = ddMapper.insertDataDicItem(ddItem);
        Preconditions.checkArgument(iCount == 1, "添加失败！");
        return ResultFactory.ok();
    }

    /**
     * 更新数据字典明细
     *
     * @param request 参数
     * @return 结果
     */
    @Override
    public Result updateDataDicItem(DataDicItemUpdateRequest request) {
        DataDicItem ddItem = this.getDataDicItemForUpdate(request);
        int uCount = ddMapper.updateDataDicItem(ddItem);
        Preconditions.checkArgument(uCount == 1, "更新失败！");
        return ResultFactory.ok();
    }

    /**
     * 删除数据字典明细
     *
     * @param dataDicItemId 数据字典明细Id
     * @return 结果
     */
    @Override
    public Result delDataDicItem(Long dataDicItemId) {
        Integer dCount = ddMapper.delDataDicItemById(dataDicItemId);
        Preconditions.checkArgument(dCount == 1, "删除失败！");
        return ResultFactory.ok();
    }

    /**
     * 构建插入对象DataDic
     *
     * @param request 传入参数
     * @return DataDic
     */
    public DataDic getDataDicForInert(DataDicInsertRequest request) {
        DataDic dataDic = new DataDic();
        dataDic.setGroupCode(request.getGroupCode());
        dataDic.setGroupName(request.getGroupName());
        dataDic.setGroupDesc(request.getGroupDesc());
        dataDic.setCreUserCode(request.getCreUserCode());
        return dataDic;
    }

    /**
     * 构建更新对象DataDic
     *
     * @param request 传入参数
     * @return DataDic
     */
    public DataDic getDataDicForUpdate(DataDicUpdateRequest request) {
        DataDic dataDic = new DataDic();
        dataDic.setId(request.getId());
        dataDic.setGroupName(request.getGroupName());
        dataDic.setGroupDesc(request.getGroupDesc());
        dataDic.setUpdUserCode(request.getUpdUserCode());
        dataDic.setVersion(request.getVersion());
        return dataDic;
    }

    /**
     * 构建插入对象DataDicItem
     *
     * @param request 参数
     * @return DataDicItem
     */
    public DataDicItem getDataDicItemForInsert(DataDicItemInsertRequest request) {
        DataDicItem ddItem = new DataDicItem();
        ddItem.setDataDicId(request.getDataDicId());
        ddItem.setDataItemCode(request.getDataItemCode());
        ddItem.setDataItemName(request.getDataItemName());
        ddItem.setDataItemDesc(request.getDataItemDesc());
        ddItem.setCreUserCode(request.getCreUserCode());
        return ddItem;
    }

    /**
     * 构建更新对象DataDicItem
     *
     * @param request 参数
     * @return DataDicItem
     */
    public DataDicItem getDataDicItemForUpdate(DataDicItemUpdateRequest request) {
        DataDicItem ddItem = new DataDicItem();
        ddItem.setId(request.getId());
        ddItem.setDataItemName(request.getDataItemName());
        ddItem.setDataItemDesc(request.getDataItemDesc());
        ddItem.setUpdUserCode(request.getUpdUserCode());
        ddItem.setVersion(request.getVersion());
        return ddItem;
    }
}

package com.etong.pms.service;

import com.etong.pms.model.Result;
import com.etong.pms.model.dto.PriceList;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PriceListService {
    Result<PageInfo<PriceList>> getList(String classa, String serial, String productname ,Integer pageNum ,Integer pageSize)    throws Exception;
}

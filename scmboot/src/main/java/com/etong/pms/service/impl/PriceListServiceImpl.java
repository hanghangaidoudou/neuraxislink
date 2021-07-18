package com.etong.pms.service.impl;

import com.etong.pms.base.exception.CommonException;
import com.etong.pms.base.exception.ResultStatusEnums;
import com.etong.pms.model.Result;
import com.etong.pms.model.dto.PriceList;
import com.etong.pms.model.dto.UserInfo;
import com.etong.pms.repository.PriceListMapper;
import com.etong.pms.service.PriceListService;
import com.etong.pms.utils.ResultFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service //服务层
@Transactional(rollbackFor = Exception.class) //事务
public class PriceListServiceImpl implements PriceListService {
    @Resource //装载
    private PriceListMapper priceListMapper;

    @Override //对接口方法的实现
    public Result<PageInfo<PriceList>> getList(String classa, String serial, String productname ,Integer pageNum ,Integer pageSize)   throws Exception {


        if(classa.equals("All"))
        {
            classa="";
        }
        if(serial.equals("All"))
        {
            serial="";
        }
        productname=productname.toLowerCase();

            PageHelper.startPage(pageNum, pageSize, true);
            List<PriceList> priceLists = priceListMapper.getList( classa,  serial,  productname);
            PageInfo<PriceList> pageInfos = new PageInfo<>(priceLists);
            return ResultFactory.of(pageInfos);
        }


}

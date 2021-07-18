package com.etong.pms.repository;

import com.etong.pms.model.dto.PriceList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PriceListMapper {

  List<PriceList> getList(@Param("classa") String classa,@Param("serial") String serial,@Param("productname") String productname);
}

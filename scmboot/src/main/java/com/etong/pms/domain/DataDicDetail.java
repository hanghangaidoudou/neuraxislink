package com.etong.pms.domain;

import com.etong.pms.base.domain.BaseObj;
import lombok.Data;

import java.util.List;

/**
 * 字典类
 *
 * @author  王超华
 */
@Data
public class DataDicDetail extends DataDic {

  /**
   * 明细列表
   */
  public List<DataDicItem> items;
}

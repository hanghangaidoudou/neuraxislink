package com.etong.pms.domain;

import com.etong.pms.base.domain.BaseObj;
import java.io.Serializable;
import lombok.Data;

/**
 * 数据项目类
 *
 * @author  王超华
 */
@Data
public class DataDicItem extends BaseObj {
  /**
   * 数据Id
   */
  public Long dataDicId;
  /**
   * 数据项目代码
   */
  public String dataItemCode;
  /**
   * 数据项目名称
   */
  public String dataItemName;
  /**
   * 数据项目描述
   */
  public String dataItemDesc;

}

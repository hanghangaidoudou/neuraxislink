package com.etong.pms.domain;

import com.etong.pms.base.domain.BaseObj;
import java.util.List;
import lombok.Data;

/**
 * 字典类
 *
 * @author  王超华
 */
@Data
public class DataDic extends BaseObj {
  /**
   * id
   */
  public Long id;
  /**
   * 数据代码
   */
  public String groupCode;
  /**
   * 数据名称
   */
  public String groupName;
  /**
   * 数据描述
   */
  public String groupDesc;

}

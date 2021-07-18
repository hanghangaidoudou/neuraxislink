package com.etong.pms.domain;

import com.etong.pms.base.domain.BaseObj;
import lombok.Data;

/**
 * 字典类
 *
 * @author  王超华
 */
@Data
public class Dept extends BaseObj {
  /**
   * id
   */
//  public String id;
  /**
   * 部门代码
   */
  public String code;
  /**
   * 部门名称
   */
  public String name;
  /**
   * 数据描述
   */
  public String desc;

}

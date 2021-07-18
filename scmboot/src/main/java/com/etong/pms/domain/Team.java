package com.etong.pms.domain;

import com.etong.pms.base.domain.BaseObj;
import lombok.Data;

/**
 * 团队类
 *
 * @author  王超华
 */
@Data
public class Team extends BaseObj {

  /**
   * 团队代码
   */
  public String code;
  /**
   * 团队名称
   */
  public String name;
  /**
   * 数据描述
   */
  public String desc;
  /**
   * 所属部门Id
   */
  public Long deptId;

}

package com.etong.pms.base.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * @ClassName BaseObj
 * @Author 王超华
 */
@Data
public class BaseObj implements Serializable {

  /**
   * id
   */
  public Long id;
  /**
   * 版本号
   */
  public Integer version;
  /**
   * 创建者
   */
  public Long creUserCode;
  /**
   * 创建时间
   */
  public String creTime;
  /**
   * 更新者
   */
  public Long updUserCode;
  /**
   * 更新时间
   */
  public String updTime;

  /**
   * 删除标识
   */
  public String deleteFlag;
}

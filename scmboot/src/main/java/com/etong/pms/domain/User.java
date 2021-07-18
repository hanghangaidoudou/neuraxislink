package com.etong.pms.domain;

import com.etong.pms.base.domain.BaseObj;
import java.io.Serializable;
import lombok.Data;

/**
 * 用户类
 *
 * @author  王超华
 */
@Data
public class User extends BaseObj {
  /**
   * 用户code
   */
  public Long userCode;
  /**
   * 用户名称
   */
  public String userName;
  /**
   * 密码
   */
  public String password;
  /**
   * 角色
   */
  public String roles;
  /**
   * 电话
   */
  public String tel;
  /**
   * 邮箱
   */
  public String email;
  /**
   * 性别 0：男；1：女
   */
  public Integer sex;

  /**
   * 部门ID
   */
  public Long deptId;
  /**
   * 团队ID
   */
  public Long teamId;
  /**
   * 在职状态  0：在职，1：离职
   */
  public String state;

  /**
   * 入职时间
   */
  public String entryDate;
  /**
   * 离职时间
   */
  public String leaveDate;

  public User(){}
  public User(Long code,String name){
    this.userCode = code;
    this.userName = name;
  }
}

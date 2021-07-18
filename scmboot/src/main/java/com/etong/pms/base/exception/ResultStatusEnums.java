package com.etong.pms.base.exception;

/**
 * 返回码枚举
 *
 * @author wch
 */
public enum ResultStatusEnums {
  /**
   * 成功
   */
  SUCCESS(0, "SUCCESS"),
  /**
   * 异常
   */
  FAIL(-1, "FAIL"),

  LOGIN_ERROR(9999, "登录失败，用户名或密码错误！"),
  SYS_ERROR(8888, "系统异常！请联系系统管理员！"),

  /* 9开头的代码为异常情况 */
  DATADICITEMCODE_ISEXIST(8995, "数据项目代码已经存在"),
  DATADICCODE_ISEXIST(8996, "数据代码已经存在"),
  INSERT_ERROR(8997, "插入失败"),
  REQ_PARAM_ERROR(8998, "请求参数异常"),
  SERVICE_ERROR(8999, "系统异常"),

  USERCODE_EXIST(1000, "代码在系统中存在"),
  OLDPASSWORD_ERROR(1001, "原密码不正确"),

  DEPTINFO_NULL(1003, "部门信息不存在"),
  /**
   * 异常枚举
   */
  AGENT_BALANCE_ERROR(111, "代理商风险金账户信息异常"),
  UPDATE_DATA_FAILD(222, "%s-数据更新失败.更新条数 = %d");

  private Integer code;

  private String message;

  ResultStatusEnums(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public Integer getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}

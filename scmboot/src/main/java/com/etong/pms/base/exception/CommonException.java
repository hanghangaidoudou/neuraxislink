package com.etong.pms.base.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通用异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommonException extends RuntimeException {

  private static final long serialVersionUID = -548455191911019195L;
  /**
   * 异常对应的返回码
   */
  private Integer code;
  /**
   * 异常对应的描述信息
   */
  private String message;

  public CommonException() {
  }

  public CommonException(Integer code, String message, Throwable cause) {
    super(cause);
    this.code = code;
    this.message = message;
  }

  public CommonException(ResultStatusEnums resultStatus) {
    super();
    this.code = resultStatus.getCode();
    this.message = resultStatus.getMessage();
  }

  public CommonException(ResultStatusEnums codeEnum, Object... args) {
    this.message = String.format(codeEnum.getMessage(), args);
    this.code = codeEnum.getCode();
  }
}

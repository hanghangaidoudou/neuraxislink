package com.etong.pms.model;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用返回对象
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {

  private static final long serialVersionUID = -5753923992801848515L;

  /**
   * 返回码
   */
  private Integer code;

  /**
   * 返回信息
   */
  private String message;

  /**
   * data
   */
  private T data;

  /**
   * Instantiates a new wrapper.
   *
   * @param code the code
   * @param message the message
   */
  public Result(Integer code, String message) {
    this(code, message, null);
  }

  /**
   * Instantiates a new wrapper.
   *
   * @param code the code
   * @param message the message
   * @param data the data
   */
  public Result(Integer code, String message, T data) {
    super();
    this.code(code).message(message).data(data);
  }

  /**
   * Sets the 编号 , 返回自身的引用.
   *
   * @param code the new 编号
   * @return the wrapper
   */
  private Result<T> code(Integer code) {
    this.setCode(code);
    return this;
  }

  /**
   * Sets the 信息 , 返回自身的引用.
   *
   * @param message the new 信息
   * @return the wrapper
   */
  private Result<T> message(String message) {
    this.setMessage(message);
    return this;
  }

  /**
   * Sets the 结果数据 , 返回自身的引用.
   *
   * @param data the new 结果数据
   * @return the wrapper
   */
  private Result<T> data(T data) {
    this.setData(data);
    return this;
  }
}

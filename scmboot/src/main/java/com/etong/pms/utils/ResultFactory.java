package com.etong.pms.utils;

import com.etong.pms.model.Result;
import com.etong.pms.base.exception.CommonException;
import com.etong.pms.base.exception.ResultStatusEnums;

/**
 * 返回对象工厂
 */
public class ResultFactory {

  private ResultFactory() {
  }

  /**
   * 无信息正常返回
   */
  public static Result ok() {
    return new Result(0, "成功");
  }

  /**
   * 获取返回码正常的对象, 通用情况下使用
   *
   * @param e data
   * @param <E> data 泛型
   * @return 正常情况数据
   */
  public static <E> Result<E> of(E e) {
    return new Result<>(0, "SUCCESS", e);
  }

  /**
   * 获取仅有返回码和信息的对象, 用于异常情况
   *
   * @param code 返回码
   * @param message 返回值
   * @return 仅返回码和信息
   */
  public static <E> Result<E> newError(Integer code, String message) {
    return new Result<>(code, message, null);
  }

  /**
   * 获取仅有返回码和信息的对象, 用于异常情况
   *
   * @param e 通用异常
   * @return 仅返回码和信息
   */
  public static <E> Result<E> newError(CommonException e) {
    return new Result<>(e.getCode(), e.getMessage(), null);
  }

  /**
   * 获取仅有返回码和信息的对象, 用于异常情况
   *
   * @param resultStatus 返回枚举
   * @return 仅返回码和信息
   */
  public static <E> Result<E> newError(ResultStatusEnums resultStatus) {
    return new Result<>(resultStatus.getCode(), resultStatus.getMessage(), null);
  }
}

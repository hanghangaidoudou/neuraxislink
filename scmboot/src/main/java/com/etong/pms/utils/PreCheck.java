package com.etong.pms.utils;


import com.etong.pms.base.exception.CommonException;
import com.etong.pms.base.exception.ResultStatusEnums;
import java.util.List;
import org.springframework.util.StringUtils;

/**
 * 快捷异常信息
 *
 * @author 冯月帅
 * @date 2019/6/1 15:50
 */
public class PreCheck {

    /**
     * 判断业务条件，抛出异常
     *
     * @param expression 判断条件，不满足时执行异常
     * @param errorEnums 异常类型
     */
    public static void checkBusiness(boolean expression, ResultStatusEnums errorEnums) {
        if (!expression) {
            throw new CommonException(errorEnums);
        }
    }

    /**
     * 检查参数,抛出异常信息为 10011001 - 参数异常
     *
     * @param expression 判断条件，不满足时报错
     */
    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new CommonException(ResultStatusEnums.REQ_PARAM_ERROR);
        }
    }

    public static void checkArgument(boolean expression, ResultStatusEnums statusEnums) {
        if (!expression) {
            throw new CommonException(statusEnums);
        }
    }

    public static void checkArgument(boolean expression, ResultStatusEnums statusEnums, Object... args) {
        if (!expression) {
            throw new CommonException(statusEnums, args);
        }
    }

    /**
     * 检查对象是否为null, 异常类型自己传入
     *
     * @param reference 判断对象
     * @param <T> 泛型
     * @return 如果非空则返回对象
     */
    public static <T> T checkNotNull(T reference, ResultStatusEnums errorEnums) {
        if (reference == null) {
            throw new CommonException(errorEnums);
        }
        return reference;
    }

    public static <T> T checkNotNull(T reference, ResultStatusEnums errorEnums, Object... agrs) {
        if (reference == null) {
            throw new CommonException(errorEnums, agrs);
        }
        return reference;
    }

    /**
     * 检查对象是否为null, 为空时异常类型为NullPointerException
     *
     * @param reference 判断对象
     * @param <T> 泛型
     * @return 如果非空则返回对象
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    /**
     * 检查字符串是否为空, 是则报指定异常
     *
     * @param checkStr 判断对象
     * @return 如果非空则返回字符串
     */
    public static String checkNotEmpty(String checkStr, ResultStatusEnums errorEnums) {
        if (StringUtils.isEmpty(checkStr)) {
            throw new CommonException(errorEnums);
        }
        return checkStr;
    }

    /**
     * 检查列表是否为空, 是则报指定异常
     *
     * @param checkList 判断对象
     * @return 如果非空则返回列表
     */
    public static <T> List<T> checkNotEmpty(List<T> checkList, ResultStatusEnums errorEnums) {
        checkNotNull(checkList, errorEnums);
        if (checkList.isEmpty()) {
            throw new CommonException(errorEnums);
        }
        return checkList;
    }
}

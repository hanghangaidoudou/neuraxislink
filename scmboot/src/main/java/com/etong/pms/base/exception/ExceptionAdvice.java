package com.etong.pms.base.exception;

import com.etong.pms.model.Result;
import com.etong.pms.utils.ResultFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @ClassName ExceptionAdvice
 * @Author wch
 * @DATE 2019/8/5 21:26
 * @return
 */
@Slf4j
@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Result handleException(Exception e) {
        Result result;
        if (e instanceof CommonException) {
            log.error("业务逻辑异常:", e);
            CommonException ce = (CommonException) e;
//            result = ResultFactory.of(e);
            result = ResultFactory.newError(ce.getCode(),ce.getMessage());
            return result;

        }
        log.error("未知异常:", e);
        return ResultFactory.newError(ResultStatusEnums.SERVICE_ERROR);
    }
}

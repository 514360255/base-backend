package com.base.framework.exception;

import com.base.framework.common.ErrorCode;
import com.base.framework.utils.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResultVo<Object> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultVo.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultVo<Object> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultVo.error(ErrorCode.SYSTEM_ERROR.getCode(), "系统错误");
    }
}

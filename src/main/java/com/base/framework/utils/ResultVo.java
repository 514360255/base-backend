package com.base.framework.utils;

import com.base.framework.common.ErrorCode;
import lombok.Data;

/**
 * @Author: 郭郭
 * @CreateTime: 2023-06-21 10:40
 * @Description: 返回数据格式
 **/

@Data
public class ResultVo<T> {

    private Integer code;

    private String message;

    private Boolean success;

    private T data;

    public ResultVo() {}

    protected static <T> ResultVo<T> build(T data) {
        ResultVo<T> result = new ResultVo<T>();
        result.setData(data);
        return result;
    }

    public static <T> ResultVo<T> build(T body, Integer code, String message, Boolean success) {
        ResultVo<T> result = build(body);
        result.setCode(code);
        result.setMessage(message);
        result.setSuccess(success);
        return result;
    }

    public static <T> ResultVo<T> build(T body, ErrorCode resultCodeEnum, Boolean success) {
        ResultVo<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        result.setSuccess(success);
        return result;
    }

    public static<T> ResultVo<T> ok(T data){
        return build(data, ErrorCode.SUCCESS, true);
    }

    public static<T> ResultVo<T> fail(ErrorCode exceptionEnum){
        return build(null, 500, ErrorCode.OPERATION_ERROR.getMessage(), false);
    }

    public ResultVo<T> code(Integer code){
        this.setCode(code);
        return this;
    }


}

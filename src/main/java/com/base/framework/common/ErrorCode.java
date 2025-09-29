package com.base.framework.common;

import lombok.Getter;

/**
 * 自定义错误码
 * @author guojiuling
 */
@Getter
public enum ErrorCode {

    SUCCESS(200, "ok"),
    PARAMS_ERROR(400, "请求参数错误"),
    NOT_LOGIN_ERROR(401, "未登录"),
    NO_AUTH_ERROR(401, "无权限"),
    NO_ACCOUNT(400, "用户不存在，或已被删除"),
    NOT_FOUND_ERROR(404, "请求数据不存在"),
    FORBIDDEN_ERROR(403, "禁止访问"),
    SYSTEM_ERROR(500, "系统内部异常"),
    REPEAT_ERROR(500, "数据重复，请检查"),
    OPERATION_ERROR(500, "操作失败");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}

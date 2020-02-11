package com.gx.cloud.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    USER_ALERDY_EXIST(1,"当前用户已经存在"),

    TOKEN_EXPIRED(2,"token令牌已经过期"),

    SUCCESS(101,"SUCCESS"),

    FAILURE(102,"FAILURE"),

    USER_NEED_AUTHORITIES(201,"用户未登录"),

    USER_LOGIN_FAILED(202,"用户账号或密码错误"),

    USER_LOGIN_SUCCESS(203,"用户登录成功"),

    USER_NO_ACCESS(204,"用户无权访问"),

    USER_LOGOUT_SUCCESS(205,"用户登出成功"),

    TOKEN_IS_BLACKLIST(206,"此token为黑名单"),

    LOGIN_IS_OVERDUE(207,"登录已失效");

    private Integer code;

    private String message;

    ExceptionCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}

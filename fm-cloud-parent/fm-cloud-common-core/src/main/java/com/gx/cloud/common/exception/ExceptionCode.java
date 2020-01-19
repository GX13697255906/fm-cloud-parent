package com.gx.cloud.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {


    USER_ALERDY_EXIST(1,"当前用户已经存在"),

    TOKEN_EXPIRED(2,"token令牌已经过期");

    private Integer code;

    private String message;

    ExceptionCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}

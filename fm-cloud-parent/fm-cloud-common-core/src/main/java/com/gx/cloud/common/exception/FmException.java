package com.gx.cloud.common.exception;

public class FmException extends RuntimeException {

    public FmException(ExceptionCode exceptionCode){
        super(exceptionCode.getMessage());
    }

    public FmException(String message){
        super(message);
    }

}

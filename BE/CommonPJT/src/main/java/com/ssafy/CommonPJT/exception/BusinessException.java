package com.ssafy.CommonPJT.exception;

public class BusinessException extends RuntimeException{
    private String errorCode;

    public BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
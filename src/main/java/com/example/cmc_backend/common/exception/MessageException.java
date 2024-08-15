package com.example.cmc_backend.common.exception;

import com.example.cmc_backend.common.exception.errorCode.BaseErrorCode;

import lombok.Getter;

@Getter
public class MessageException extends BaseException{
    public MessageException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

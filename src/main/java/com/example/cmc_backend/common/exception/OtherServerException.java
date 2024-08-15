package com.example.cmc_backend.common.exception;

import com.example.cmc_backend.common.exception.errorCode.BaseErrorCode;

import lombok.Getter;

@Getter
public class OtherServerException extends BaseException{
    public OtherServerException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

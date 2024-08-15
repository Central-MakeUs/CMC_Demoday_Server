package com.example.cmc_backend.common.exception;

import com.example.cmc_backend.common.exception.errorCode.BaseErrorCode;

import lombok.Getter;

@Getter
public class InternalServerException extends BaseException {
    public InternalServerException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

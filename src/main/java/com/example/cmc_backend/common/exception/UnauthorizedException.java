package com.example.cmc_backend.common.exception;

import com.example.cmc_backend.common.exception.errorCode.BaseErrorCode;

import lombok.Getter;


@Getter
public class UnauthorizedException extends BaseException {

    public UnauthorizedException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

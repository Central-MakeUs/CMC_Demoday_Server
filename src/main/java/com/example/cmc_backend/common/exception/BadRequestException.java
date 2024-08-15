package com.example.cmc_backend.common.exception;


import com.example.cmc_backend.common.exception.errorCode.BaseErrorCode;

import lombok.Getter;


@Getter
public class BadRequestException extends BaseException {

    public BadRequestException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
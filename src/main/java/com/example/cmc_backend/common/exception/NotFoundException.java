package com.example.cmc_backend.common.exception;

import com.example.cmc_backend.common.exception.errorCode.BaseErrorCode;

import lombok.Getter;

@Getter
public class NotFoundException extends BaseException  {

    public NotFoundException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

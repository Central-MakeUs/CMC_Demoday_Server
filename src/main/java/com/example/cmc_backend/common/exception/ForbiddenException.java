package com.example.cmc_backend.common.exception;


import com.example.cmc_backend.common.exception.errorCode.BaseErrorCode;

import lombok.Getter;


@Getter
public class ForbiddenException extends BaseException {

    public ForbiddenException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

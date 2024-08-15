package com.example.cmc_backend.common.exception;

import com.example.cmc_backend.common.exception.errorCode.BaseErrorCode;

import lombok.Getter;

@Getter
public class UserException extends BaseException {

    private String errorMessageWithSocialType;

    public UserException(BaseErrorCode errorCode) {
        super(errorCode);
    }

    public UserException(BaseErrorCode errorCode, String errorMessageWithSocialType) {
        super(errorCode);
        this.errorMessageWithSocialType = errorMessageWithSocialType;
    }
}

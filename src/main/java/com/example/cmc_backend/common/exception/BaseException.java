package com.example.cmc_backend.common.exception;


import com.example.cmc_backend.common.exception.errorCode.BaseErrorCode;
import com.example.cmc_backend.common.exception.errorCode.ErrorReason;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private BaseErrorCode errorCode;

    public ErrorReason getErrorReason() {
        return this.errorCode.getErrorReason();
    }

    public ErrorReason getErrorReasonHttpStatus(){
        return this.errorCode.getErrorReasonHttpStatus();
    }

}

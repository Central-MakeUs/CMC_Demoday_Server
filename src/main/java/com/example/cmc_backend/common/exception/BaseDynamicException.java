package com.example.cmc_backend.common.exception;

import java.util.HashMap;
import java.util.Map;

import com.example.cmc_backend.common.exception.errorCode.BaseErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseDynamicException extends RuntimeException {
    BaseErrorCode status;
    Map<String,String> data;

    public BaseDynamicException(BaseErrorCode errorReason, HashMap<String, String> data) {
        this.status = errorReason;
        this.data = data;
    }


}

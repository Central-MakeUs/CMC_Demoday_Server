package com.example.cmc_backend.user.exception;

import static org.springframework.http.HttpStatus.*;

import java.lang.reflect.Field;
import java.util.Objects;

import org.springframework.http.HttpStatus;

import com.example.cmc_backend.common.annotiation.ExplainError;
import com.example.cmc_backend.common.exception.errorCode.BaseErrorCode;
import com.example.cmc_backend.common.exception.errorCode.ErrorReason;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserAuthErrorCode implements BaseErrorCode {
    /*
       인증 관련 에러코드
    */

    @ExplainError("이미 해당 정보로 신청한 경우 발생되는 에러 코드")
    ALREADY_EXIST_USER(BAD_REQUEST,"USER_001", "이미 해당 정보로 신청하였습니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().message(message).code(code).isSuccess(false).build();
    }

    @Override
    public String getExplainError() throws NoSuchFieldException {
        Field field = this.getClass().getField(this.name());
        ExplainError annotation = field.getAnnotation(ExplainError.class);
        return Objects.nonNull(annotation) ? annotation.value() : this.getMessage();
    }

    @Override
    public ErrorReason getErrorReasonHttpStatus(){
        return ErrorReason.builder().message(message).code(code).isSuccess(false).httpStatus(httpStatus).build();
    }
}

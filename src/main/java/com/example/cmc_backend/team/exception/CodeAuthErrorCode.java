
package com.example.cmc_backend.team.exception;

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
public enum CodeAuthErrorCode implements BaseErrorCode {
    /*
       인증 관련 에러코드
    */

    NOT_MATCHED_CODE(BAD_REQUEST,"TEAM_002", "코드가 올바르지 않습니다."),
    EXISTS_COIN(BAD_REQUEST,"TEAM_003", "이미 인증된 코드가 존재합니다.");


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

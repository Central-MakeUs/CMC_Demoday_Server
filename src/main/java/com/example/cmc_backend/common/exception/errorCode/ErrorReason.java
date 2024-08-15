package com.example.cmc_backend.common.exception.errorCode;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorReason {
	private HttpStatus httpStatus;
	private final boolean isSuccess;
	private final String code;
	private final String message;
	private final Map<String, String> result;

	public boolean getIsSuccess(){
		return isSuccess;
	}
}

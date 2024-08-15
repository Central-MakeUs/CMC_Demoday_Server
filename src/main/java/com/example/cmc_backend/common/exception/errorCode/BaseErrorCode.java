package com.example.cmc_backend.common.exception.errorCode;

public interface BaseErrorCode {
	public ErrorReason getErrorReason();

	String getExplainError() throws NoSuchFieldException;

	public ErrorReason getErrorReasonHttpStatus();


}

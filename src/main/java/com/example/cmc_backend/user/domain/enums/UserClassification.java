package com.example.cmc_backend.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserClassification {
	STUDENT("학생"),
	WORKER("직장인"),
	C_LEVEL("C 레벨");

	private final String classification;
}

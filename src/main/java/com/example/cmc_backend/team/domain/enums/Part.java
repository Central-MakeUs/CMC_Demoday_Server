package com.example.cmc_backend.team.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Part {
	WEB("웹"),
	BACK_END("백엔드"),
	IOS("iOS"),
	ANDROID("안드로이드"),
	DESIGN("디자인"),
	PLANNER("기획자"),
	ETC("기타");

	private final String part;
}

package com.example.cmc_backend.team.domain.enums;

import static com.example.cmc_backend.team.domain.enums.PartDetail.*;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Part {
	WEB("웹", Developer),
	BACK_END("백엔드", Developer),
	IOS("iOS",Developer),
	ANDROID("안드로이드",Developer),
	DESIGN("디자인",Designer),
	PLANNER("기획자", PM);

	private final String part;
	private final PartDetail partDetail;
}

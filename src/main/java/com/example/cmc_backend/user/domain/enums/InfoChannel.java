package com.example.cmc_backend.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InfoChannel {
	EMAIL("이메일"),
	SMS("문자"),
	KAKAO("카카오톡"),
	FACEBOOK("페이스북"),
	INSTAGRAM("인스타그램"),
	NAVER("네이버"),
	ETC("기타");

	private final String channel;
}

package com.example.cmc_backend.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParticipationClassification{
	CLUB("동아리"),
	ETC("기타"),
	ACQUAINTANCE("지인");
	public final String classification;
}

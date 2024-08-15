package com.example.cmc_backend.team.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TeamClassification {
	CMC("CMC"),
	UMC("UMC"),
	START_UP("창업팀");
	private final String classification;
}

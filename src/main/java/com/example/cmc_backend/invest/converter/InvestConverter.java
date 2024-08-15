package com.example.cmc_backend.invest.converter;

import com.example.cmc_backend.invest.domain.InvestCoin;
import com.example.cmc_backend.team.domain.Team;
import com.example.cmc_backend.user.domain.User;

public class InvestConverter {
	public static InvestCoin toInvestCoin(User user, Team team) {
		return InvestCoin.builder()
			.user(user)
			.team(team)
			.build();
	}
}

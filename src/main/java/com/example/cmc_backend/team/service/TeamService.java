package com.example.cmc_backend.team.service;

import static com.example.cmc_backend.team.exception.CodeAuthErrorCode.*;
import static com.example.cmc_backend.team.exception.TeamErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cmc_backend.common.exception.NotFoundException;
import com.example.cmc_backend.invest.converter.InvestConverter;
import com.example.cmc_backend.invest.repository.InvestCoinRepository;
import com.example.cmc_backend.team.converter.TeamConverter;
import com.example.cmc_backend.team.domain.Team;
import com.example.cmc_backend.team.dto.TeamReq;
import com.example.cmc_backend.team.dto.TeamRes;
import com.example.cmc_backend.team.repository.TeamRepository;
import com.example.cmc_backend.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamService {
	private final TeamRepository teamRepository;
	private final InvestCoinRepository investCoinRepository;

	public List<TeamRes.TeamList> getTeamList() {
		List<Team> teamList = teamRepository.findAll();
		return TeamConverter.toTeamList(teamList);
	}

	public TeamRes.TeamDetail getTeamDetail(Long teamId) {
		Team team = teamRepository.findById(teamId).orElseThrow(() -> new NotFoundException(NOT_EXISTS_TEAM));
		return TeamConverter.toTeamDetail(team);
	}

	@Transactional
	public void codeAuth(User user, TeamReq.CodeAuth code) {
		Team team = teamRepository.findById(code.getTeamId()).orElseThrow(() -> new NotFoundException(NOT_EXISTS_TEAM));
		if (!team.getCode().equals(code.getCode())) {
			throw new NotFoundException(NOT_MATCHED_CODE);
		}
		if(investCoinRepository.existsByUserAndTeam(user, team)) {
			throw new NotFoundException(EXISTS_COIN);
		}
		investCoinRepository.save(InvestConverter.toInvestCoin(user, team));
	}
}

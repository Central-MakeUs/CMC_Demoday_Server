package com.example.cmc_backend.team.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.example.cmc_backend.team.domain.Team;
import com.example.cmc_backend.team.domain.TeamMember;
import com.example.cmc_backend.team.dto.TeamRes;

public class TeamConverter {
	public static List<TeamRes.TeamList> toTeamList(List<Team> teamList) {
		return teamList.stream()
				.map(team -> TeamRes.TeamList.builder()
						.teamId(team.getId())
						.boothLocation(team.getBoothLocation())
						.classification(team.getClassification())
						.build()).collect(Collectors.toList());
	}

	public static TeamRes.TeamDetail toTeamDetail(Team team) {
		return TeamRes.TeamDetail
			.builder()
			.teamId(team.getId())
			.logoUrl(team.getLogoUrl())
			.teamName(team.getTeamName())
			.projectImgUrl(team.getProjectImgUrl())
			.serviceIntroduction(team.getServiceIntroduction())
			.explanation(team.getExplanation())
			.mainFunctions(team.getMainFunctions())
			.teamMembers(toTeamMemberList(team.getTeamMembers()))
			.build();
	}

	private static List<TeamRes.TeamMember> toTeamMemberList(List<TeamMember> teamMembers) {
		return teamMembers.stream()
				.map(teamMember -> TeamRes.TeamMember.builder()
						.teamUserid(teamMember.getId())
						.name(teamMember.getName())
						.part(teamMember.getPart())
						.partDetail(teamMember.getPart().getPartDetail())
						.build()).collect(Collectors.toList());
	}
}

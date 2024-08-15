package com.example.cmc_backend.team.dto;

import java.util.List;

import com.example.cmc_backend.team.domain.enums.Part;
import com.example.cmc_backend.team.domain.enums.PartDetail;
import com.example.cmc_backend.team.domain.enums.TeamClassification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TeamRes {
	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class TeamList{
		private Long teamId;
		private String boothLocation;
		private TeamClassification classification;
	}

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class TeamDetail {
		private Long teamId;
		private String logoUrl;
		private String teamName;
		private String projectImgUrl;
		private String serviceIntroduction;
		private String explanation;
		private String mainFunctions;
		private List<TeamMember> teamMembers;
	}

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class TeamMember{
		private Long teamUserid;
		private String name;
		private Part part;
		private PartDetail partDetail;
	}
}

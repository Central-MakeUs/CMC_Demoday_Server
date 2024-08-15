package com.example.cmc_backend.team.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cmc_backend.common.CommonResponse;
import com.example.cmc_backend.common.annotiation.ApiErrorCodeExample;
import com.example.cmc_backend.common.exception.errorCode.UserAuthErrorCode;
import com.example.cmc_backend.team.dto.TeamReq;
import com.example.cmc_backend.team.dto.TeamRes;
import com.example.cmc_backend.team.exception.CodeAuthErrorCode;
import com.example.cmc_backend.team.exception.TeamErrorCode;
import com.example.cmc_backend.team.service.TeamService;
import com.example.cmc_backend.user.domain.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
@Tag(name = "팀", description = "팀 관련 API")
public class TeamController {
	private final TeamService teamService;

	@GetMapping("/booth")
	@ApiErrorCodeExample(UserAuthErrorCode.class)
	@Operation(summary = "부스 목록 조회", description = "부스 목록 조회")
	public CommonResponse<List<TeamRes.TeamList>> getTeamList(){
		List<TeamRes.TeamList> teamList = teamService.getTeamList();
		return CommonResponse.onSuccess(teamList);
	}

	@GetMapping("/{teamId}")
	@ApiErrorCodeExample({TeamErrorCode.class, UserAuthErrorCode.class})
	@Operation(summary = "팀 상세 조회", description = "팀 상세 조회")
	public CommonResponse<TeamRes.TeamDetail> getTeamDetail(@Parameter(description = "팀 아이디") @PathVariable("teamId") Long teamId){
		return CommonResponse.onSuccess(teamService.getTeamDetail(teamId));
	}

	@PostMapping("/code-auth")
	@ApiErrorCodeExample({CodeAuthErrorCode.class, UserAuthErrorCode.class, TeamErrorCode.class})
	@Operation(summary = "코드 인증", description = "코드 인증")
	public CommonResponse<String> codeAuth(@AuthenticationPrincipal User user, @RequestBody TeamReq.CodeAuth codeAuth){
		teamService.codeAuth(user, codeAuth);
		return CommonResponse.onSuccess("인증 완료");
	}

}

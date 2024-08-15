package com.example.cmc_backend.team.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cmc_backend.team.service.TeamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {
	private final TeamService teamService;
	/*
	팀목록 조회
	 */

	/*
	팀 상세 조회
	 */
}

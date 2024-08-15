package com.example.cmc_backend.team.service;

import org.springframework.stereotype.Service;

import com.example.cmc_backend.team.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamService {
	private final TeamRepository teamRepository;
}

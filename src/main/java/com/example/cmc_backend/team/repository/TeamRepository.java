package com.example.cmc_backend.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cmc_backend.team.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}

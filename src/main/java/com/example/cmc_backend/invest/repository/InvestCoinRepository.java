package com.example.cmc_backend.invest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cmc_backend.invest.domain.InvestCoin;
import com.example.cmc_backend.team.domain.Team;
import com.example.cmc_backend.user.domain.User;

public interface InvestCoinRepository extends JpaRepository<InvestCoin, Long> {

	boolean existsByUserAndTeam(User user, Team team);
}

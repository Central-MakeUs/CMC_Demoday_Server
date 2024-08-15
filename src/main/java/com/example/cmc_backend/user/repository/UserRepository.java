package com.example.cmc_backend.user.repository;

import com.example.cmc_backend.user.domain.User;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String userId);

    Optional<User> findByUsernameAndPhoneNumber(String username, String phoneNumber);

    boolean existsByUsernameAndPhoneNumber(String username, String phoneNumber);

    Optional<User> findByUsername(String s);
}

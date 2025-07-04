package com.example.player_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity,Long> {
    Optional<PlayerEntity> findByName(String username);
    long deleteByName(String username);
}

package com.example.seebook.domain.level.repository;

import com.example.seebook.domain.level.domain.LevelInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<LevelInfo, Long> {

    Optional<LevelInfo> findByUserId(Long userId);
}

package com.example.seebook.domain.level.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "level_info")
public class LevelInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long levelId;

    private Long userId;

    private int level;
    private int levelCount;

    @Builder
    public LevelInfo(Long userId, int level, int levelCount) {
        this.userId = userId;
        this.level = level;
        this.levelCount = levelCount;
    }
}

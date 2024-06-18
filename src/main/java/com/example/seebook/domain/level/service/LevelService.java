package com.example.seebook.domain.level.service;

import com.example.seebook.domain.level.domain.Level;
import com.example.seebook.domain.level.domain.LevelInfo;
import com.example.seebook.domain.level.repository.LevelRepository;
import com.example.seebook.global.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LevelService {
    private final LevelRepository levelRepository;

    public void plusLevelCount(Long userId) {
        LevelInfo levelInfo = levelRepository.findByUserId(userId)
                .orElseThrow(UserException.NotFoundUserException::new);
        levelInfo.plusLevelCount();
        if (Level.canUpgradeLevel(levelInfo.getLevel(), levelInfo.getLevelCount())) {
            levelInfo.upgradeLevel();
        }
        levelRepository.save(levelInfo);
    }

    public void minusLevelCount(Long userId) {
        LevelInfo levelInfo = levelRepository.findByUserId(userId)
                .orElseThrow(UserException.NotFoundUserException::new);
        levelInfo.minusLevelCount();
        if (Level.canDowngradeLevel(levelInfo.getLevelCount())) {
            levelInfo.downgradeLevel();
        }
        levelRepository.save(levelInfo);
    }

}

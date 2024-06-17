package com.example.seebook.domain.level.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Level {
    ONE(1, 2),
    TWO(2, 5),
    THREE(3, 10),
    FOUR(4, 20),
    FIVE(5, 30),
    SIX(6, 40),
    SEVEN(7, 60),
    EIGHT(8, 80),
    NINE(9, 100),
    TEN(10, 100000000);

    private final int level;
    private final int levelCount;


    public static boolean canUpgradeLevel(int level, int levelCount) {
        return getLevelMaxCount(level) <= levelCount;
    }
    public static boolean canDowngradeLevel(int levelCount) {
        return levelCount < 0;
    }

    public static int getLevelMaxCount(int level) {
        return switch (level) {
            case 1 -> ONE.getLevelCount();
            case 2 -> TWO.getLevelCount();
            case 3 -> THREE.getLevelCount();
            case 4 -> FOUR.getLevelCount();
            case 5 -> FIVE.getLevelCount();
            case 6 -> SIX.getLevelCount();
            case 7 -> SEVEN.getLevelCount();
            case 8 -> NINE.getLevelCount();
            case 9 -> TEN.getLevelCount();
            default -> throw new IllegalArgumentException("Invalid level: " + level);
        };
    }

}

package com.example.seebook.domain.profile.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class JoinResponseDTO {
    private String profileImage;
    private String nickname;
    private String email;
    private int level;
    private int levelCount;

    @Builder
    public JoinResponseDTO(String profileImage, String nickname, String email, int level, int levelCount) {
        this.profileImage = profileImage;
        this.nickname = nickname;
        this.email = email;
        this.level = level;
        this.levelCount = levelCount;
    }
}

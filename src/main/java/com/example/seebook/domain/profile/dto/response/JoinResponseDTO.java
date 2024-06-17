package com.example.seebook.domain.profile.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JoinResponseDTO {
    private String profileImage;
    private String nickname;
    private String email;
    private int level;
    private int levelCount;

}

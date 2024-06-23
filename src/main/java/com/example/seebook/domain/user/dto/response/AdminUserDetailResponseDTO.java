package com.example.seebook.domain.user.dto.response;

import com.example.seebook.domain.suspend.dto.SuspendDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminUserDetailResponseDTO {
    private String profileImage;
    private String email;
    private String nickname;
    private String name;
    private String gender;
    private String role;
    private String password;
    private SuspendDTO suspend;

    @Builder
    public AdminUserDetailResponseDTO(String profileImage, String email, String nickname, String name, String gender, String role, String password, SuspendDTO suspend) {
        this.profileImage = profileImage;
        this.email = email;
        this.nickname = nickname;
        this.name = name;
        this.gender = gender;
        this.role = role;
        this.password = password;
        this.suspend = suspend;
    }
}

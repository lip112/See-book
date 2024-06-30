package com.example.seebook.domain.user.dto.requset;

import com.example.seebook.domain.suspend.dto.SuspendDTO;
import com.example.seebook.global.config.UserDefaultConfig;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.example.seebook.global.config.UserDefaultConfig.DEFAULT_PASSWORD;
import static com.example.seebook.global.config.UserDefaultConfig.DEFAULT_PROFILE_IMAGE;

@Getter
public class AdminUserModifyRequestDTO {
    private Long userId;
    private String profileImage;
    private String email;
    private String nickname;
    private String name;
    private String gender;
    private String role;
    private String password;
    private SuspendDTO suspend;
    private boolean resetProfileImage;
    private boolean resetPassword;

    public void isResetProfileImage() {
        if (resetProfileImage) {
            this.profileImage = DEFAULT_PROFILE_IMAGE;
        }
    }

    public void isResetPassword() {
        if (resetPassword) {
            this.password = DEFAULT_PASSWORD;
        }
    }
}

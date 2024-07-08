package com.example.seebook.domain.user.dto.requset;

import com.example.seebook.domain.suspend.dto.SuspendDTO;
import com.example.seebook.global.config.UserDefaultConfig;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static com.example.seebook.global.config.UserDefaultConfig.DEFAULT_PASSWORD;
import static com.example.seebook.global.config.UserDefaultConfig.DEFAULT_PROFILE_IMAGE;

@Getter
public class AdminUserModifyRequestDTO {
    @NotNull
    private Long userId;
    @NotNull
    private String nickname;
    @NotNull
    private String gender;
    @NotNull
    private String role;

    private LocalDateTime endDate;

    @NotNull
    private boolean resetProfileImage;
    @NotNull
    private boolean resetPassword;
}

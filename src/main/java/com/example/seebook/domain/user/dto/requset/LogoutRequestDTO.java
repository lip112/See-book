package com.example.seebook.domain.user.dto.requset;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LogoutRequestDTO {

    @NotNull
    private Long userId;

    @NotBlank
    private String provider;

    @NotNull
    private Long kakaoId;
}

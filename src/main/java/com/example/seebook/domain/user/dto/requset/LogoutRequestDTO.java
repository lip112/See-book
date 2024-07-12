package com.example.seebook.domain.user.dto.requset;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class LogoutRequestDTO {
    @NotBlank
    private String provider;
 }

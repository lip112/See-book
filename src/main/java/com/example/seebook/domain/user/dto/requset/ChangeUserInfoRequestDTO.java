package com.example.seebook.domain.user.dto.requset;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeUserInfoRequestDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}

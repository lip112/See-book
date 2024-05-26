package com.example.seebook.domain.user.dto.requset;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangePasswordRequestDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}

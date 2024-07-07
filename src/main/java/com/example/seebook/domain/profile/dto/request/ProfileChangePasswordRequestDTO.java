package com.example.seebook.domain.profile.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class ProfileChangePasswordRequestDTO {
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$",
            message = "비밀번호는 8~16자리의 영문 대소문자, 숫자, 특수문자를 포함해야 합니다.")
    private String password;
}

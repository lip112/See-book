package com.example.seebook.domain.user.dto.requset;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeUserInfoRequestDTO {
    @NotBlank
    @Email(message = "이메일 형식으로 작성 되지 않았습니다.")
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$",
            message = "비밀번호는 8~16자리의 영문 대소문자, 숫자, 특수문자를 포함해야 합니다.")
    private String password;
}

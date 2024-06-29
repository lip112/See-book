package com.example.seebook.domain.user.dto.oauth2;

import com.example.seebook.domain.user.domain.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
public class KaKaoSignUpRequestDTO {

    @NotNull
    private Long kakaoId;

    @NotBlank
    @Email(message = "이메일 형식으로 작성 되지 않았습니다.")
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$",
            message = "비밀번호는 8~16자리의 영문 대소문자, 숫자, 특수문자를 포함해야 합니다.")
    private String password;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{2,8}$", message = "닉네임은 2~8자리의 문자와 숫자로 이루어져야 합니다.")
    private String nickname;

    @NotBlank
    @Pattern(regexp = "^[가-힣]{3,4}$", message = "이름은 3~4자리의 한글로 이루어져야 합니다.")
    private String name;

    @NotNull(message = " MALE 혹은 FEMALE만 입력 가능합니다")
    private Gender gender;

    @NotBlank()
    @Pattern(regexp = "\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])", message = "생일은 yyyy-MM-dd 형식이어야 하며, MM은 01~12, dd는 01~31이어야 합니다.")
    private String birthday;

    @NotBlank
    @Pattern(regexp = "[0-9]{10,11}", message = "하이픈 없는 10~11자리 숫자를 입력해주세요")
    private String phoneNumber;

    @Builder
    public KaKaoSignUpRequestDTO(Long kakaoId, String email, String password, String nickname, String name, Gender gender, String birthday, String phoneNumber) {
        this.kakaoId = kakaoId;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
    }
}

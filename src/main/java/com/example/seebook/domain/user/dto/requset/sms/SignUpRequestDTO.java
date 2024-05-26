package com.example.seebook.domain.user.dto.requset.sms;

import com.example.seebook.domain.user.domain.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
public class SignUpRequestDTO {

    @NotBlank
    @Email(message = "이메일 형식으로 작성 되지 않았습니다.")
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;

    @NotBlank
    private String name;

    @NotBlank(message = " MALE 혹은 FEMALE만 입력 가능합니다")
    private Gender gender;

    @NotBlank(message = "0000-00-00 형식으로 입력 가능합니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String birthday;

    @NotBlank
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{4})[.-]?(\\d{4})$", message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
    private String phoneNumber;

    @Builder
    public SignUpRequestDTO(String email, String password, String nickname, String name, Gender gender, String birthday, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
    }
}

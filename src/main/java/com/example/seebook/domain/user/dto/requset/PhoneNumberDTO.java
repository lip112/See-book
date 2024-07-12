package com.example.seebook.domain.user.dto.requset;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class PhoneNumberDTO {
    @NotBlank
    @Pattern(regexp = "[0-9]{10,11}", message = "하이픈 없는 11자리 숫자를 입력해주세요")
    private String phoneNumber;
}

package com.example.seebook.domain.user.dto.oauth2;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Oauth2SignUpResponseDTO extends LoginResponse {
    private Long kakaoId;
    private String name;
    private String email;
    private String phoneNumber;
    private String gender;
    private String birthday;

    public static Oauth2SignUpResponseDTO form(Oauth2DTO oauth2DTO) {
        Oauth2SignUpResponseDTO seebook = Oauth2SignUpResponseDTO.builder()
                .kakaoId(oauth2DTO.getKakaoId())
                .name(oauth2DTO.getName())
                .email(oauth2DTO.getEmail())
                .phoneNumber(oauth2DTO.getPhoneNumber())
                .gender(oauth2DTO.getGender())
                .birthday(oauth2DTO.getBirthday())
                .build();
        seebook.setSuccess(false);
        return seebook;
    }
}

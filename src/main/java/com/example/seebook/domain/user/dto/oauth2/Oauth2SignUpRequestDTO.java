package com.example.seebook.domain.user.dto.oauth2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Oauth2SignUpRequestDTO extends LoginResponse {
    private String name;
    private String email;
    private String phoneNumber;
    private String gender;
    private String birthday;

    public static Oauth2SignUpRequestDTO form(Oauth2DTO oauth2DTO) {
        Oauth2SignUpRequestDTO seebook = Oauth2SignUpRequestDTO.builder()
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

package com.example.seebook.domain.user.dto.oauth2;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Oauth2DTO {

    private Long kakaoId;
    private String name;
    private String email;
    private String phoneNumber;
    private String gender;
    private String birthday;

    @Builder
    public Oauth2DTO(Long kakaoId, String name, String email, String phoneNumber, String gender, String birthday) {
        this.kakaoId = kakaoId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birthday = birthday;
    }
}

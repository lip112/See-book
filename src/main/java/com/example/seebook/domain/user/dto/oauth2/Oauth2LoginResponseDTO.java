package com.example.seebook.domain.user.dto.oauth2;

import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.user.dto.response.Suspend;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Oauth2LoginResponseDTO extends LoginResponse  {
    private Long user_id;
    private String provider;
    private Suspend suspend;

    public static Oauth2LoginResponseDTO form(User user, Suspend suspend){
        Oauth2LoginResponseDTO seebook = Oauth2LoginResponseDTO.builder()
                .user_id(user.getUserId())
                .provider("kakao")
                .suspend(suspend)
                .build();
        seebook.setSuccess(true);
        return seebook;
    };
}

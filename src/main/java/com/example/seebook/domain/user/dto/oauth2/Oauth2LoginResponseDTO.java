package com.example.seebook.domain.user.dto.oauth2;

import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.suspend.dto.SuspendDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Oauth2LoginResponseDTO extends LoginResponse  {
    private String provider;
    private SuspendDTO suspend;
    public static Oauth2LoginResponseDTO form(SuspendDTO suspend){
        Oauth2LoginResponseDTO seebook = Oauth2LoginResponseDTO.builder()
                .provider("kakao")
                .suspend(suspend)
                .build();
        seebook.setSuccess(true);
        return seebook;
    };
}

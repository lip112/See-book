package com.example.seebook.domain.user.dto.response;

import com.example.seebook.domain.suspend.dto.SuspendDTO;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.user.dto.oauth2.LoginResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LoginResponseDTO {
    private String provider;
    private SuspendDTO suspend;

    public static LoginResponseDTO form(SuspendDTO suspend){
        return LoginResponseDTO.builder()
                .provider("seebook")
                .suspend(suspend)
                .build();
    };
}

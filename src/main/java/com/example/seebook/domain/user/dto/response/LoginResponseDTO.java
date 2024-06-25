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
public class LoginResponseDTO extends LoginResponse {
    private Long userId;
    private String provider;
    private SuspendDTO suspend;

    public static LoginResponseDTO form(User user, SuspendDTO suspend){
        return LoginResponseDTO.builder()
                .userId(user.getUserId())
                .provider("seebook")
                .suspend(suspend)
                .build();
    };
}

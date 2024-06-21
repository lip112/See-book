package com.example.seebook.domain.user.dto.response;

import com.example.seebook.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LoginResponseDTO {
    private Long userId;
    private String provider;
    private Suspend suspend;

    public static LoginResponseDTO form(User user, Suspend suspend){
        return LoginResponseDTO.builder()
                .userId(user.getUserId())
                .provider("seebook")
                .suspend(suspend)
                .build();
    };
}

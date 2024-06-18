package com.example.seebook.domain.user.dto.response;

import com.example.seebook.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LoginResponseDTO {
    private Long user_id;
    private String provider;
    private Suspend suspend;

    public static LoginResponseDTO form(User user, Suspend suspend){
        return LoginResponseDTO.builder()
                .user_id(user.getUserId())
                .provider("seebook")
                .suspend(suspend)
                .build();
    };
}

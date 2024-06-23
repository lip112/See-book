package com.example.seebook.domain.user.dto;

import com.example.seebook.domain.role.domain.RoleCode;
import com.example.seebook.domain.user.domain.Gender;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserDTO {
    private Long userId;
    private String email;
    private String name;
    private String nickname;
    private Gender gender;
    private String role;
    private LocalDateTime createdDate;
    @Builder
    public UserDTO(Long userId, String email, String name, String nickname, Gender gender, RoleCode role, LocalDateTime createdDate) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.gender = gender;
        this.role = role.getDescription();
        this.createdDate = createdDate;
    }
}

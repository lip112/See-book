package com.example.seebook.domain.user.dto.requset;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AdminUserDetailRequestDTO {
    @NotNull
    private Long userId;
}

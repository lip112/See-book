package com.example.seebook.domain.user.dto.requset;

import lombok.Getter;

import java.util.List;

@Getter
public class AdminUserDeleteRequestDTO {
    private List<Long> userId;
}

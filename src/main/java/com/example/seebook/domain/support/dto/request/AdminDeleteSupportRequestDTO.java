package com.example.seebook.domain.support.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class AdminDeleteSupportRequestDTO {
    private List<Long> supportId;
}

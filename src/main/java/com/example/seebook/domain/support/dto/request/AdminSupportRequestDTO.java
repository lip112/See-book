package com.example.seebook.domain.support.dto.request;

import com.example.seebook.domain.support.domain.SupportType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AdminSupportRequestDTO {
    @NotNull
    private Long supportId;
    @NotNull
    private String replyContent;
}

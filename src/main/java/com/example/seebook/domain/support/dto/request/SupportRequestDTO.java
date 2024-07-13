package com.example.seebook.domain.support.dto.request;

import com.example.seebook.domain.support.domain.SupportType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SupportRequestDTO {
    @NotNull
    private SupportType supportType;
    @NotNull
    private String content;
}

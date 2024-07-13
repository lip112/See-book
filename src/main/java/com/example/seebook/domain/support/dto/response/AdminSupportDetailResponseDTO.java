package com.example.seebook.domain.support.dto.response;

import com.example.seebook.domain.support.dto.SupportDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class AdminSupportDetailResponseDTO {
    private SupportDTO support;

    @Builder
    public AdminSupportDetailResponseDTO(SupportDTO support) {
        this.support = support;
    }
}

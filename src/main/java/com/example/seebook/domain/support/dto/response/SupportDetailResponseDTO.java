package com.example.seebook.domain.support.dto.response;

import com.example.seebook.domain.support.dto.SupportDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SupportDetailResponseDTO {
    private SupportDTO support;

    @Builder
    public SupportDetailResponseDTO(SupportDTO support) {
        this.support = support;
    }
}

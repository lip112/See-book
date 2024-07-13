package com.example.seebook.domain.support.dto.response;

import com.example.seebook.domain.support.dto.SupportDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SupportListResponseDTO {
    private Long totalSupportCount;
    private Long endPage;
    private List<SupportDTO> support;

    @Builder
    public SupportListResponseDTO(Long totalSupportCount, Long endPage, List<SupportDTO> support) {
        this.totalSupportCount = totalSupportCount;
        this.endPage = endPage;
        this.support = support;
    }
}

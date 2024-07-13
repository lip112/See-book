package com.example.seebook.domain.support.service;

import com.example.seebook.domain.support.domain.Support;
import com.example.seebook.domain.support.dto.SupportDTO;
import com.example.seebook.domain.support.dto.response.AdminSupportDetailResponseDTO;
import com.example.seebook.domain.support.dto.response.AdminSupportListResponseDTO;
import com.example.seebook.domain.support.repository.SupportRepository;
import com.example.seebook.global.exception.SupportException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminSupportService {
    private final SupportRepository supportRepository;

    public AdminSupportListResponseDTO getSupportList(int page) {
        return supportRepository.getAdminList((page - 1) *10, page*10 -1 );
    }

    public AdminSupportDetailResponseDTO getSupportDetail(Long supportId) {
        Support support = supportRepository.findById(supportId)
                .orElseThrow(SupportException.NotFoundSupportException::new);
        if (support.isProcessed()){
            SupportDTO supportDTO = SupportDTO.builder()
                    .email(support.getUserId().getEmail())
                    .name(support.getUserId().getName())
                    .nickname(support.getUserId().getNickname())
                    .supportId(support.getSupportId())
                    .supportType(support.getSupportType())
                    .isProcessed(support.isProcessed())
                    .requestDate(support.getRequestDate())
                    .content(support.getContent())
                    .replyContent(support.getReplyContent())
                    .replyDate(support.getReplyDate())
                    .build();
            return AdminSupportDetailResponseDTO.builder()
                    .support(supportDTO)
                    .build();
        } else {
            SupportDTO supportDTO = SupportDTO.builder()
                    .email(support.getUserId().getEmail())
                    .name(support.getUserId().getName())
                    .nickname(support.getUserId().getNickname())
                    .supportId(support.getSupportId())
                    .supportType(support.getSupportType())
                    .isProcessed(support.isProcessed())
                    .requestDate(support.getRequestDate())
                    .content(support.getContent())
                    .build();
            return AdminSupportDetailResponseDTO.builder()
                    .support(supportDTO)
                    .build();
        }
    }

    public void processSupport(Long supportId, String replyContent) {
        Support support = supportRepository.findById(supportId)
                .orElseThrow(SupportException.NotFoundSupportException::new);
        support.reply(replyContent);
        supportRepository.save(support);
    }
}

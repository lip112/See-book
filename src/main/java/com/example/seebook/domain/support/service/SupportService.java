package com.example.seebook.domain.support.service;

import com.example.seebook.domain.support.domain.Support;
import com.example.seebook.domain.support.domain.SupportType;
import com.example.seebook.domain.support.dto.SupportDTO;
import com.example.seebook.domain.support.dto.response.SupportDetailResponseDTO;
import com.example.seebook.domain.support.dto.response.SupportListResponseDTO;
import com.example.seebook.domain.support.repository.SupportRepository;
import com.example.seebook.domain.support.repository.SupportRepositoryCustom;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.global.exception.SupportException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupportService {
    private final SupportRepository supportRepository;

    public SupportListResponseDTO getSupportList(int page, Long userId) {
        return supportRepository.getList(userId, (page - 1) *10, page*10 -1);
    }

    public SupportDetailResponseDTO getSupportDetail(Long supportId) {
        Support support = supportRepository.findById(supportId)
                .orElseThrow(SupportException.NotFoundSupportException::new);
        if (support.isProcessed()){
            SupportDTO supportDTO = SupportDTO.builder()
                    .supportType(support.getSupportType())
                    .content(support.getContent())
                    .isProcessed(support.isProcessed())
                    .requestDate(support.getRequestDate())
                    .replyContent(support.getReplyContent())
                    .replyDate(support.getReplyDate())
                    .build();
            return SupportDetailResponseDTO.builder()
                    .support(supportDTO)
                    .build();
        } else {
            SupportDTO supportDTO = SupportDTO.builder()
                    .supportType(support.getSupportType())
                    .content(support.getContent())
                    .isProcessed(support.isProcessed())
                    .requestDate(support.getRequestDate())
                    .build();
            return SupportDetailResponseDTO.builder()
                    .support(supportDTO)
                    .build();
        }
    }

    public void requestSupport(SupportType supportType, String content, User user) {
        Support support = Support.builder()
                .supportType(supportType)
                .content(content)
                .user(user)
                .build();
        supportRepository.save(support);
    }

}

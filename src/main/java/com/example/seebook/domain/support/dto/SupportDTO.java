package com.example.seebook.domain.support.dto;

import com.example.seebook.domain.support.domain.SupportType;
import com.example.seebook.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SupportDTO {
    private Long supportId;
    private Long userId;
    private SupportType supportType;
    private String content;
    private LocalDateTime requestDate;
    private LocalDateTime replyDate;
    private boolean isProcessed;
    private String replyContent;
    private String email;
    private String name;
    private String nickname;

    @Builder
    public SupportDTO(Long supportId, Long userId, SupportType supportType, String content, LocalDateTime requestDate, LocalDateTime replyDate, boolean isProcessed, String replyContent, String email, String name, String nickname) {
        this.supportId = supportId;
        this.userId = userId;
        this.supportType = supportType;
        this.content = content;
        this.requestDate = requestDate;
        this.replyDate = replyDate;
        this.isProcessed = isProcessed;
        this.replyContent = replyContent;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
    }
}

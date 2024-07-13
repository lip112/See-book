package com.example.seebook.domain.support.domain;

import com.example.seebook.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Support {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supportId;

    @ManyToOne
    private User userId;

    @Enumerated(EnumType.STRING)
    private SupportType supportType;

    private String content;

    private LocalDateTime requestDate;

    private LocalDateTime replyDate;

    private boolean isProcessed;

    private String replyContent;

    @Builder
    public Support(User user, SupportType supportType, String content) {
        this.userId = user;
        this.supportType = supportType;
        this.content = content;
        this.requestDate = LocalDateTime.now();
        this.isProcessed = false;
    }

    public void reply(String replyContent) {
        this.replyContent = replyContent;
        this.replyDate = LocalDateTime.now();
        this.isProcessed = true;
    }
}

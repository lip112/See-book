package com.example.seebook.domain.profile.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class ChangeNicknameRequestDTO {

    @NotBlank
    private Long userId;

    @NotBlank
    private String nickname;
}

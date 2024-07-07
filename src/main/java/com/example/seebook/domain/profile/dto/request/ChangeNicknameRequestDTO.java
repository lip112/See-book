package com.example.seebook.domain.profile.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class ChangeNicknameRequestDTO {
    @NotBlank
    private String nickname;
}

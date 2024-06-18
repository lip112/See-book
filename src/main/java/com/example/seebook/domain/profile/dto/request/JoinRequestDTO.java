package com.example.seebook.domain.profile.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class JoinRequestDTO {

    @NotBlank
    private Long userId;
}

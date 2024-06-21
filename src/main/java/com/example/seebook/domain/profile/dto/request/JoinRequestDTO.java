package com.example.seebook.domain.profile.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class JoinRequestDTO {

    @NotNull
    private Long userId;
}

package com.example.seebook.domain.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FindEmailResponseDTO {
    private final String email;

    @Builder
    public FindEmailResponseDTO(String email) {
        this.email = email;
    }
}

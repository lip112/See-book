package com.example.seebook.domain.user.dto.response;

import lombok.Getter;

@Getter
public class FindEmailResponseDTO {
    private final String email;

    public FindEmailResponseDTO(String email) {
        this.email = email;
    }
}

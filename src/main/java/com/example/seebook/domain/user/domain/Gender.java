package com.example.seebook.domain.user.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("male"),
    FEMALE("female");

    private final String value;
}

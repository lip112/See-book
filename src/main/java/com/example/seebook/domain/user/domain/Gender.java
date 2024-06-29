package com.example.seebook.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE, FEMALE;

    @JsonCreator
    public static Gender fromString(String key) {
        if (key == null) {
            return null;
        }
        try {
            return Gender.valueOf(key.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("MALE | FEMALE 중 하나를 입력해주세요.");
        }
    }

    @JsonValue
    public String toValue() {
        return name();
    }
}

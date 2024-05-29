package com.example.seebook.domain.roletype.domain;

import lombok.Getter;

@Getter
public enum RoleCode {
    ADMIN("ADMIN", "관리자"),
    USER("USER", "일반 사용자");

    private final String role_type;
    private final String description;

    RoleCode(String role_type, String description) {
        this.role_type = role_type;
        this.description = description;
    }

}

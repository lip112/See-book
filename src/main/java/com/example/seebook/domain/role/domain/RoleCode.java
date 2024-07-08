package com.example.seebook.domain.role.domain;

import lombok.Getter;

@Getter
public enum RoleCode {
    ADMIN("ADMIN", "관리자"),
    USER("USER", "사용자");

    private final String role_type;
    private final String description;

    RoleCode(String role_type, String description) {
        this.role_type = role_type;
        this.description = description;
    }

    public static RoleCode fromDescription(String description) {
        for (RoleCode roleCode : values()) {
            if (roleCode.getDescription().equalsIgnoreCase(description)) {
                return roleCode;
            }
        }
        throw new IllegalArgumentException("사용자 혹은 관리자만 입력 가능합니다.: " + description);
    }

}

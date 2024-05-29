package com.example.seebook.domain.roletype.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name = "role_type")
public class RoleType  {

    @Id
    @Enumerated(EnumType.STRING)
    private RoleCode roleCode;

    private String roleType;

    public RoleType(RoleCode roleCode) {
        this.roleCode = roleCode;
        this.roleType = roleCode.getDescription();
    }

    public void upgradeRole() {
        if (this.roleCode == RoleCode.USER) {
            this.roleCode = RoleCode.ADMIN;
            this.roleType = roleCode.getDescription();
        }
    }
    public void downgradeRole() {
        if (this.roleCode == RoleCode.ADMIN) {
            this.roleCode = RoleCode.USER;
            this.roleType = roleCode.getDescription();
        }
    }
}

package com.example.seebook.domain.role.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name = "role_info")
public class RoleInfo {

    @Id
    @Enumerated(EnumType.STRING)
    private RoleCode Code;

    private String Description;

    public RoleInfo(RoleCode Code) {
        this.Code = Code;
        this.Description = Code.getDescription();
    }

    public void upgradeRole() {
        if (this.Code == RoleCode.USER) {
            this.Code = RoleCode.ADMIN;
            this.Description = Code.getDescription();
        }
    }
    public void downgradeRole() {
        if (this.Code == RoleCode.ADMIN) {
            this.Code = RoleCode.USER;
            this.Description = Code.getDescription();
        }
    }


}

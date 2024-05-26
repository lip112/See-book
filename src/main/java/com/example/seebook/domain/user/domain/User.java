package com.example.seebook.domain.user.domain;

import com.example.seebook.domain.roletype.entity.RoleType;
import com.example.seebook.global.audit.BaseTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String nickname;

    private String name;

    @ManyToOne
    @JoinColumn(name = "role_type")
    private RoleType role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phoneNumber;

    private LocalDate birthday;

    @Builder
    public User(Long id, String email, RoleType role, String password, String nickname, String name, Gender gender, String phoneNumber, String birthday) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birthday = LocalDate.parse(birthday);
    }

    public void changePassword(String password) {
        this.password = password;
    }
}

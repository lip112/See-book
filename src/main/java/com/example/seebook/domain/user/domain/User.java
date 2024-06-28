package com.example.seebook.domain.user.domain;

import com.example.seebook.domain.role.domain.RoleInfo;
import com.example.seebook.global.audit.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private Long kakaoId;

    @Column(unique = true)
    private String email;

    private String password;

    private String nickname;

    private String name;

    @ManyToOne
    @JoinColumn(name = "role_type")
    private RoleInfo role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phoneNumber;

    private LocalDate birthday;

    @Builder
    public User(Long id,Long kakaoId, String email, RoleInfo role, String password, String nickname, String name, Gender gender, String phoneNumber, String birthday) {
        this.userId = id;
        this.email = email;
        this.kakaoId = kakaoId;
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
    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changeGender(Gender gender) {
        this.gender = gender;
    }

    public void changeRole(RoleInfo role) {
        this.role = role;
    }
}

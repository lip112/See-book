package com.example.seebook.domain.profile.domain;

import com.example.seebook.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.seebook.global.config.UserDefaultConfig.DEFAULT_PROFILE_IMAGE;

@Getter
@NoArgsConstructor
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    private Long userId;
    private String uploadName;
    private String originalName;
    private String imageUrl;

    @Builder
    public Profile(Long userId, String uploadName, String originalName, String imageUrl) {
        this.userId = userId;
        this.uploadName = uploadName;
        this.originalName = originalName;
        this.imageUrl = imageUrl;
    }

    public void changeImage(String uploadName, String imageUrl, String originalName) {
        this.uploadName = uploadName;
        this.originalName = originalName;
        this.imageUrl = imageUrl;
    }

    public void resetDefaultImage() {
        this.uploadName = "default_profile.png";
        this.originalName = "default_profile.png";
        this.imageUrl = DEFAULT_PROFILE_IMAGE;
    }

}

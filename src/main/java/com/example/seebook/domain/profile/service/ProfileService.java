package com.example.seebook.domain.profile.service;

import com.example.seebook.domain.profile.domain.Profile;
import com.example.seebook.domain.profile.dto.request.ProfileReviewListRequestDTO;
import com.example.seebook.domain.profile.dto.response.JoinResponseDTO;
import com.example.seebook.domain.profile.repository.ProfileRepository;
import com.example.seebook.global.exception.UserException;
import com.example.seebook.global.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final S3Uploader s3Uploader;

    public void getProfileWithReviewList(ProfileReviewListRequestDTO profileReviewListRequestDTO) {
        profileRepository.getProfileWithReviewListDTO(profileReviewListRequestDTO.getUserId(),
                (profileReviewListRequestDTO.getPage()-1) * 10, profileReviewListRequestDTO.getPage()*10 -1);
    }
    public JoinResponseDTO joinProfile(Long userId) {
        return profileRepository.joinProfile(userId);
    }

    public void addSingUpDefaultProfileImage(Long userId) {
        profileRepository.save(Profile.builder()
                        .userId(userId)
                        .imageUrl("https://bookbuket.s3.ap-northeast-2.amazonaws.com/default.png")
                        .uploadName("default.png")
                        .originalName("default.png")
                .build());
    }

    @Transactional
    public void changeImage(Long userId, MultipartFile multipartFile) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(UserException.NotFoundUserException::new);

        System.out.println("profile.getImageLink() = " + profile.getImageUrl());
        s3Uploader.deleteImageFromS3(profile.getImageUrl());

        String uploadUrl = s3Uploader.upload(multipartFile);
        String uploadName = uploadUrl.substring(uploadUrl.length() - 34); // uuid 30 + .png(4)

        profile.changeImage(uploadName, uploadUrl, multipartFile.getOriginalFilename());

        profileRepository.save(profile);
    }

    public void resetDefaultProfileImage(Long userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(UserException.NotFoundUserException::new);
        profile.resetDefaultImage();
    }
}

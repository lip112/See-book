package com.example.seebook.domain.profile.service;

import com.example.seebook.domain.profile.dto.request.ProfileWithReviewListRequestDTO;
import com.example.seebook.domain.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public void getProfileWithReviewList(ProfileWithReviewListRequestDTO profileWithReviewListRequestDTO) {

    }
}

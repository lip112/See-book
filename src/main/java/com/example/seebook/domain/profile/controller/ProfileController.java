package com.example.seebook.domain.profile.controller;

import com.example.seebook.domain.profile.dto.request.ProfileWithReviewListRequestDTO;
import com.example.seebook.domain.profile.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/review")
    public void getProfileWithReviewList(@Valid @RequestBody ProfileWithReviewListRequestDTO profileWithReviewListRequestDTO) {

    }
}

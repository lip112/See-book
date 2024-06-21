package com.example.seebook.domain.user.controller;

import com.example.seebook.domain.profile.service.ProfileService;
import com.example.seebook.domain.user.dto.oauth2.LoginResponse;
import com.example.seebook.domain.user.dto.oauth2.Oauth2DTO;
import com.example.seebook.domain.user.dto.requset.SignUpRequestDTO;
import com.example.seebook.domain.user.service.OauthService;
import com.example.seebook.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/oauth")
@RequiredArgsConstructor
public class Oauth2Controller {
    private final OauthService oauthService;
    private final UserService userService;
    private final ProfileService profileService;

    @GetMapping("/kakao")
    public ResponseEntity<LoginResponse> login(@RequestParam String code) {
        String accessToken = oauthService.getAccessTokenFromKakao(code);
        Oauth2DTO userInfo = oauthService.getUserInfoFromKakao(accessToken);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.processOAuth2Login(userInfo));
    }

    @PostMapping("/total-signup")
    public ResponseEntity<?> totalSignUp(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        Long userId = userService.signUp(signUpRequestDTO);
        profileService.addSingUpDefaultProfileImage(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}

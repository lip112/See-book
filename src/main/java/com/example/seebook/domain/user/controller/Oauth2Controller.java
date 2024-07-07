package com.example.seebook.domain.user.controller;

import com.example.seebook.domain.level.service.LevelService;
import com.example.seebook.domain.profile.service.ProfileService;
import com.example.seebook.domain.suspend.service.SuspendService;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.user.dto.oauth2.*;
import com.example.seebook.domain.user.dto.requset.SignUpRequestDTO;
import com.example.seebook.domain.user.dto.response.LoginResponseDTO;
import com.example.seebook.domain.user.service.OauthService;
import com.example.seebook.domain.user.service.UserService;
import com.example.seebook.global.jwt.CustomUserDetails;
import com.example.seebook.global.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletResponse;
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
    private final SuspendService suspendService;
    private final JwtProvider jwtProvider;
    private final LevelService levelService;

    @GetMapping("/kakao/login")
    public ResponseEntity<LoginResponse> login(@RequestParam String code, HttpServletResponse httpResponse) {
        String kakaoAccessToken = oauthService.getAccessTokenFromKakao(code);
        Oauth2DTO userInfo = oauthService.getUserInfoFromKakao(kakaoAccessToken);
        //카카오로 이미 회원가입이 되어있는지 확인
        if (userService.checkRegistration(userInfo.getPhoneNumber())) {
            User user = userService.loginToKakao(userInfo);
            String accessToken = jwtProvider.createAccessToken(CustomUserDetails.from(user));
            httpResponse.setHeader("Authorization", "Bearer " + accessToken);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Oauth2LoginResponseDTO.form(suspendService.getSuspend(user.getUserId())));
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Oauth2SignUpResponseDTO.form(userInfo));
        }
    }

    @PostMapping("kakao/total-signup")
    public ResponseEntity<?> totalSignUp(@Valid @RequestBody KaKaoSignUpRequestDTO kaKaoSignUpRequestDTO) {
        Long userId = userService.signUpKaKao(kaKaoSignUpRequestDTO);
        profileService.addSingUpDefaultProfileImage(userId);
        levelService.createLevelInfo(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}

package com.example.seebook.domain.user.controller;

import com.example.seebook.domain.user.dto.oauth2.LoginResponse;
import com.example.seebook.domain.user.dto.oauth2.Oauth2DTO;
import com.example.seebook.domain.user.dto.requset.SignUpRequestDTO;
import com.example.seebook.domain.user.dto.response.LoginResponseDTO;
import com.example.seebook.domain.user.service.AuthService;
import com.example.seebook.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user/oauth")
@RequiredArgsConstructor
public class Oauth2Controller {
    private final AuthService authService;
    private final UserService userService;
    @GetMapping("/kakao")
    public ResponseEntity<LoginResponse> login(@RequestParam String code) {
        String accessToken = authService.getAccessTokenFromKakao(code);
        Oauth2DTO userInfo = authService.getUserInfoFromKakao(accessToken);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.processOAuth2Login(userInfo));
    }

    @PostMapping("/total-signup")
    public ResponseEntity<?> totalSignUp(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        userService.signUp(signUpRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}

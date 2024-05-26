package com.example.seebook.domain.user.controller;

import com.example.seebook.domain.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/oauth")
@RequiredArgsConstructor
public class Oauth2Controller {
    private final AuthService authService;
    @GetMapping("/kakao")
    public void login(@RequestParam String code) {
        String accessTokenFromKakao = authService.getAccessTokenFromKakao(code);
        authService.getUserInfoFromKakao(accessTokenFromKakao);
    }
}

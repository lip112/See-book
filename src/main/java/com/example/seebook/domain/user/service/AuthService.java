package com.example.seebook.domain.user.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class AuthService {

//    restapi 키
    private final String clientId = "5e44324a700c1117fe9b5e9ad20383b3";
    private final String clientSecret = "R3r9TinZXZnf7Ro06AVMGHiBAS0zvLrK";
    private final String redirectUri = "http://localhost:8080/api/user/oauth/kakao";
    private final RestTemplate restTemplate = new RestTemplate();

    public String getAccessTokenFromKakao(String code) {
        String tokenUrl = "https://kauth.kakao.com/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        params.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<KakaoTokenResponse> response = restTemplate.postForEntity(tokenUrl, request, KakaoTokenResponse.class);

        return response.getBody().toOAuth2AccessToken();
    }

    public void getUserInfoFromKakao(String accessToken) {
        String userInfoUrl = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>("", headers);

        ResponseEntity<Map> response = restTemplate.exchange(userInfoUrl, HttpMethod.GET, entity, Map.class);

        Map<String, Object> attributes = response.getBody();

        System.out.println(attributes);
    }
}

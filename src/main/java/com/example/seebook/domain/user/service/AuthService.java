package com.example.seebook.domain.user.service;

import com.example.seebook.domain.user.dto.oauth2.Oauth2DTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
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

        ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(response.getBody());
                return root.path("access_token").asText();
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse access token from Kakao response", e);
            }
        } else {
            throw new RuntimeException("Failed to get access token from Kakao, response status: " + response.getStatusCode());
        }
    }

    public Oauth2DTO getUserInfoFromKakao(String accessToken) {
        String userInfoUrl = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>("", headers);

        ResponseEntity<Map> response = restTemplate.exchange(userInfoUrl, HttpMethod.GET, entity, Map.class);

        Map<String, Object> attributes = response.getBody();

        Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");

        return Oauth2DTO.builder()
                .name(kakao_account.get("name").toString())
                .email(kakao_account.get("email").toString())
                .phoneNumber(formatPhoneNumber(kakao_account.get("phone_number").toString()))
                .gender(kakao_account.get("gender").toString())
                .birthday(formatDate(kakao_account.get("birthyear").toString()+kakao_account.get("birthday").toString()))
                .build();
    }

    private String formatPhoneNumber(String phoneNumber) {
        // Step 1: 공백, 하이픈을 제거하고 "82 "를 "0"으로 대체합니다.
        String intermediate = phoneNumber.replaceAll("\\+82 ", "0").replaceAll("[\\s-]", "");

        // Step 2: 결과 반환
        return intermediate;
    }
    private String formatDate(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);

        return year + "-" + month + "-" + day;
    }
}

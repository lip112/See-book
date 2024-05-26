package com.example.seebook.domain.user.service;

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

    public Map<String, Object> getUserInfoFromKakao(String accessToken) {
        String userInfoUrl = "https://kapi.kakao.com/v2/user/me";
        HashMap<String, Object> userInfo = new HashMap<String, Object>();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>("", headers);

        ResponseEntity<Map> response = restTemplate.exchange(userInfoUrl, HttpMethod.GET, entity, Map.class);

        Map<String, Object> attributes = response.getBody();

        Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");


        Long id = (Long) attributes.get("id");
        String name = kakao_account.get("name").toString();
        String email = kakao_account.get("email").toString();
        String phone_number = kakao_account.get("phone_number").toString();
        String gender = kakao_account.get("gender").toString();
        String birthyear = kakao_account.get("birthyear").toString();
        String birthday = kakao_account.get("birthday").toString();

        //userInfo에 넣기
        userInfo.put("id", id);
        userInfo.put("name", name);
        userInfo.put("email", email);
        userInfo.put("phone_number", phone_number);
        userInfo.put("birthyear", birthyear);
        userInfo.put("birthday", birthday);
        userInfo.put("gender", gender);

        return userInfo;
    }
}

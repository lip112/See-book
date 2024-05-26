package com.example.seebook.domain.user.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KakaoTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;
    public String toOAuth2AccessToken() {
        return this.accessToken;
    }
}

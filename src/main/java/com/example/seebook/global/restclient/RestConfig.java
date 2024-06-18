package com.example.seebook.global.restclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

// 스프링 3.2 부터 도입된 RestClient
//1. 동기식 방법
//2. WebClient와 다르게 Webflex를 의존하지 않아도 됨.
@Configuration
public class RestConfig {

//나는 알라딘에서만 가져올 테니 Base를 설정해줌
    @Bean
    public AladinComponent restClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl("http://www.aladin.co.kr/ttb/api")
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(AladinComponent.class);
    }
}

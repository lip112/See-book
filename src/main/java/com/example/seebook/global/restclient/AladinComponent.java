package com.example.seebook.global.restclient;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Map;
import java.util.Objects;

@Component
@HttpExchange //해당 인터페이스가 HTTP 요청 인터페이스라고 알려줌, 이러면 인터페이스의 메서드가 HTTP 요청 메서드와 함께 매핑된다.
public interface AladinComponent {

    // base + EndPoint + 여기서 RequestParam이 추가됨
    //http://www.aladin.co.kr/ttb/api/ + ItemSearch.aspx + ?TTBKey=aladinKey&Query=Title&Output=JS&Start=1&Version=20131101&Cover=Big
    @GetExchange("/ItemSearch.aspx") //get 방식으로 요청, End Point로 /ItemSearch.aspx주소가 설정됨
    Map<String, Object> findAllByQuery(@RequestParam String TTBKey, @RequestParam String Query, @RequestParam String QueryType, @RequestParam String output,
                                        @RequestParam int start, @RequestParam int version, @RequestParam String cover);
    @GetExchange("/ItemLookUp.aspx")
    Map<String, Object> findByIsbn13(@RequestParam String TTBKey, @RequestParam String ItemId, @RequestParam String ItemIdType,
                          @RequestParam String Output, @RequestParam int version, @RequestParam String Cover);
}

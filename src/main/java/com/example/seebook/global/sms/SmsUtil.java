package com.example.seebook.global.sms;

import com.example.seebook.domain.user.dto.requset.sms.VerificationRequestDTO;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class SmsUtil {

    @Value("${coolsms.api.key}")
    private String apiKey;
    @Value("${coolsms.api.secret}")
    private String apiSecretKey;

    @Value("${coolsms.myphoneNumber}")
    private String myPhoneNumber;

    private DefaultMessageService messageService;
    private final ConcurrentHashMap<String, VerificationEntry> verificationMap = new ConcurrentHashMap<>();

    @PostConstruct
    private void init(){
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");
    }

    public SingleMessageSentResponse sendOneSMS(String to) {
        String code = String.valueOf(new Random().nextInt(899999) + 100000);
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom(myPhoneNumber);
        message.setTo(to);
        message.setText("[See-book] 아래의 인증번호를 입력해주세요\n" + code);
        verificationMap.put(to, new VerificationEntry(code, System.currentTimeMillis()));

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        return response;
    }

    public boolean verifyCode(VerificationRequestDTO verificationRequestDTO) {
        VerificationEntry entry = verificationMap.get(verificationRequestDTO.getPhoneNumber());
        if (entry != null && entry.getCode().equals(verificationRequestDTO.getOtp()) &&
                System.currentTimeMillis() - entry.getTimestamp() <= TimeUnit.MINUTES.toMillis(5)) {
            verificationMap.remove(verificationRequestDTO.getPhoneNumber()); // 검증 후 항목 제거
            return true;
        }
        return false;
    }

    @Getter
    @AllArgsConstructor
    static class VerificationEntry {
        private final String code;
        private final long timestamp;
    }

}

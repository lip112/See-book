package com.example.seebook.global.sms;

import com.example.seebook.domain.user.dto.requset.PhoneNumberDTO;
import com.example.seebook.domain.user.dto.requset.sms.VerificationRequestDTO;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class SmsService {
    private final ConcurrentHashMap<String, VerificationEntry> verificationMap = new ConcurrentHashMap<>();

    public void sendVerificationCode(PhoneNumberDTO phoneNumberDTO) {
        String code = generateVerificationCode();
        verificationMap.put(phoneNumberDTO.getPhoneNumber(), new VerificationEntry(code, System.currentTimeMillis()));
        // 코드를 전송하는 로직
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

    private String generateVerificationCode() {
        return String.valueOf(new Random().nextInt(899999) + 100000);
    }

    static class VerificationEntry {
        private final String code;
        private final long timestamp;

        public VerificationEntry(String code, long timestamp) {
            this.code = code;
            this.timestamp = timestamp;
        }

        public String getCode() {
            return code;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
}
package com.example.seebook.domain.user.controller;

import com.example.seebook.domain.user.dto.requset.ChangePasswordRequestDTO;
import com.example.seebook.domain.user.dto.requset.LoginRequestDTO;
import com.example.seebook.domain.user.dto.requset.PhoneNumberDTO;
import com.example.seebook.domain.user.dto.requset.sms.VerificationRequestDTO;
import com.example.seebook.domain.user.dto.requset.sms.SignUpRequestDTO;
import com.example.seebook.domain.user.service.UserService;
import com.example.seebook.global.exception.UserException;
import com.example.seebook.global.sms.SmsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final SmsService smsService;

    @GetMapping("/pp")
    public ResponseEntity<?> sss() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        userService.signUp(signUpRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOTP(@Valid @RequestBody PhoneNumberDTO phoneNumber) {
        smsService.sendVerificationCode(phoneNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOTP(@Valid @RequestBody VerificationRequestDTO verificationRequestDTO) {
        smsService.verifyCode(verificationRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/find-email")
    public ResponseEntity<String> findEmail(@Valid @RequestBody VerificationRequestDTO verificationRequestDTO) {
        if (smsService.verifyCode(verificationRequestDTO)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userService.findEmail(verificationRequestDTO.getPhoneNumber()));
        } else {
            throw new UserException.NotFoundEmailException();
        }
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequestDTO changePasswordRequestDTO) {
        userService.changePassword(changePasswordRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.login(loginRequestDTO));
    }
}

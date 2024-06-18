package com.example.seebook.domain.user.controller;

import com.example.seebook.domain.profile.service.ProfileService;
import com.example.seebook.domain.user.dto.requset.*;
import com.example.seebook.domain.user.dto.requset.sms.VerificationRequestDTO;
import com.example.seebook.domain.user.service.UserService;
import com.example.seebook.global.exception.UserException;
import com.example.seebook.global.sms.SmsUtil;
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
    private final ProfileService profileService;
    private final SmsUtil smsUtil;
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        Long userId = userService.signUp(signUpRequestDTO);
        profileService.singUpDefaultProfileImage(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOTP(@Valid @RequestBody PhoneNumberDTO phoneNumber) {
        smsUtil.sendOneSMS(phoneNumber.getPhoneNumber());
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOTP(@Valid @RequestBody VerificationRequestDTO verificationRequestDTO) {
        if (smsUtil.verifyCode(verificationRequestDTO)){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

    }

    @PostMapping("/find-email")
    public ResponseEntity<String> findEmail(@Valid @RequestBody VerificationRequestDTO verificationRequestDTO) {
        if (smsUtil.verifyCode(verificationRequestDTO)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userService.getEmail(verificationRequestDTO.getPhoneNumber()));
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
                .body(userService.loginToEmail(loginRequestDTO));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Valid @RequestBody LogoutRequestDTO logoutRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/validation-nickname")
    public ResponseEntity<?> validationNickname(@RequestParam("nickname") String nickname) {
        if (userService.validationNickname(nickname)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("사용 가능한 닉네임 입니다.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("중복된 닉네임 입니다.");
        }
    }
}

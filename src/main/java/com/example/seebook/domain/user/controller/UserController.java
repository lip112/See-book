package com.example.seebook.domain.user.controller;

import com.example.seebook.domain.level.service.LevelService;
import com.example.seebook.domain.profile.service.ProfileService;
import com.example.seebook.domain.review.service.ReviewService;
import com.example.seebook.domain.support.service.SupportService;
import com.example.seebook.domain.suspend.service.SuspendService;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.user.dto.requset.*;
import com.example.seebook.domain.user.dto.requset.sms.DeleteAccountRequestDTO;
import com.example.seebook.domain.user.dto.requset.sms.VerificationRequestDTO;
import com.example.seebook.domain.user.dto.response.FindEmailResponseDTO;
import com.example.seebook.domain.user.dto.response.LoginResponseDTO;
import com.example.seebook.domain.user.service.OauthService;
import com.example.seebook.domain.user.service.UserService;
import com.example.seebook.domain.wishlist.domain.Wishlist;
import com.example.seebook.domain.wishlist.service.WishlistService;
import com.example.seebook.global.exception.UserException;
import com.example.seebook.global.jwt.CustomUserDetails;
import com.example.seebook.global.jwt.JwtProvider;
import com.example.seebook.global.jwt.UserAuthorizationUtil;
import com.example.seebook.global.sms.SmsUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Validated
public class UserController {
    private final UserService userService;
    private final ProfileService profileService;
    private final SuspendService suspendService;
    private final OauthService oauthService;
    private final SmsUtil smsUtil;
    private final JwtProvider jwtProvider;
    private final LevelService levelService;
    private final SupportService supportService;
    private final ReviewService reviewService;
    private final WishlistService wishlistService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        Long userId = userService.signUp(signUpRequestDTO);
        profileService.addSingUpDefaultProfileImage(userId);
        levelService.createLevelInfo(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse httpResponse) {
        User user = userService.loginToEmail(loginRequestDTO);

        String accessToken = jwtProvider.createAccessToken(CustomUserDetails.from(user));

        httpResponse.setHeader("Authorization", "Bearer " + accessToken);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(LoginResponseDTO.form(suspendService.getSuspend(user.getUserId())));
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
        if (smsUtil.verifyCode(verificationRequestDTO)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } else {
            throw new UserException.InvalidVerificationCodeException();
        }
    }

    @PostMapping("/find-email")
    public ResponseEntity<FindEmailResponseDTO> findEmail(@Valid @RequestBody VerificationRequestDTO verificationRequestDTO) {
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new FindEmailResponseDTO(userService.getEmail(verificationRequestDTO.getPhoneNumber())));
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequestDTO changePasswordRequestDTO) {
        userService.changeHomeByPassword(changePasswordRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Valid @RequestBody LogoutRequestDTO logoutRequestDTO) {
        if (logoutRequestDTO.getProvider().equals("kakao")) {
            Long userId = UserAuthorizationUtil.getLoginUserId();
            Long kakaoId = userService.findById(userId).getKakaoId();
            oauthService.logoutAccount(kakaoId);
            SecurityContextHolder.clearContext();
            //토큰 날리기
        } else {
            //토큰 날리기
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/validation-nickname")
    public ResponseEntity<?> validationNickname(@RequestParam("nickname") String nickname) {
        if (userService.validationNickname(nickname)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @GetMapping("/validation-email")
    public ResponseEntity<FindEmailResponseDTO> validationEmail(@RequestParam("email") String email) {
        //DB에 email이 존재하면 200 없으면 400
        if (!userService.validationEmail(email)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @GetMapping("/duplication-email")
    public ResponseEntity<Void> duplicationEmail(@RequestParam("email") String email) {
        //DB에 email이 없으면 200 있으면 400
        if (userService.validationEmail(email)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<?> deleteAccount(@Valid @RequestBody DeleteAccountRequestDTO deleteAccountRequestDTO) {
        Long userId = UserAuthorizationUtil.getLoginUserId();
        User user = userService.findById(userId);
        supportService.deleteSupportByUser(user);
        reviewService.deleteReviewByUser(user);
        wishlistService.deleteWishlistByUser(user);
        if (deleteAccountRequestDTO.getProvider().equals("kakao")) {
            oauthService.deleteAccount(user.getKakaoId());
        }

        userService.deleteAccount(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}

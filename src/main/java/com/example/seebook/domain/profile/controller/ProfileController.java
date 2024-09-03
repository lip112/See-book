package com.example.seebook.domain.profile.controller;

import com.example.seebook.domain.profile.dto.request.ChangeNicknameRequestDTO;
import com.example.seebook.domain.profile.dto.request.ProfileChangePasswordRequestDTO;
import com.example.seebook.domain.profile.dto.request.ProfileReviewListRequestDTO;
import com.example.seebook.domain.profile.dto.response.JoinResponseDTO;
import com.example.seebook.domain.profile.dto.response.ProfileReviewListResponseDTO;
import com.example.seebook.domain.profile.service.ProfileService;
import com.example.seebook.domain.review.service.ReviewService;
import com.example.seebook.domain.suspend.dto.SuspendDTO;
import com.example.seebook.domain.suspend.service.SuspendService;
import com.example.seebook.domain.user.service.UserService;
import com.example.seebook.global.jwt.UserAuthorizationUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;
    private final SuspendService suspendService;
    private final UserService userService;


    @PostMapping("/review-list")
    public ResponseEntity<ProfileReviewListResponseDTO> getProfileReviewList(@Valid @RequestBody ProfileReviewListRequestDTO profileReviewListRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(profileService.getProfileWithReviewList(profileReviewListRequestDTO));
    }

    @GetMapping("/join")
    public ResponseEntity<JoinResponseDTO> joinProfile(){
        Long userId = UserAuthorizationUtil.getLoginUserId();
        JoinResponseDTO joinResponseDTO = profileService.joinProfile(userId);
        SuspendDTO suspend = suspendService.getSuspend(userId);
        joinResponseDTO.addSuspendDTO(suspend);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(joinResponseDTO);
    }

    @PostMapping("/change-image")//RequestParam는 쿼리매개변수도 관여하지만 폼데이터를 핸들링할때도 사용
    public ResponseEntity<?> changeImage(@RequestParam("image") MultipartFile image) {
        Long userId = UserAuthorizationUtil.getLoginUserId();
        profileService.changeImage(userId, image);
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

    @PostMapping("/change-nickname")
    public ResponseEntity<?> changeNickname(@Valid @RequestBody ChangeNicknameRequestDTO changeNicknameRequestDTO) {
        Long userId = UserAuthorizationUtil.getLoginUserId();
        userService.changeNickname(userId, changeNicknameRequestDTO.getNickname());
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ProfileChangePasswordRequestDTO profileChangePasswordRequestDTO) {
        Long userId = UserAuthorizationUtil.getLoginUserId();
        userService.changeProfilePassword(profileChangePasswordRequestDTO, userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
    @PostMapping("/validation-password")
    public ResponseEntity<?> validationPassword(@Valid @RequestBody ProfileChangePasswordRequestDTO profileChangePasswordRequestDTO) {
        Long userId = UserAuthorizationUtil.getLoginUserId();
        if (userService.validationProfilePassword(profileChangePasswordRequestDTO, userId)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("사용 가능한 비밀번호 입니다.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("비밀번호가 일치하지 않습니다.");
        }
    }
}

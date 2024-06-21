package com.example.seebook.domain.profile.controller;

import com.example.seebook.domain.profile.dto.request.ChangeNicknameRequestDTO;
import com.example.seebook.domain.profile.dto.request.JoinRequestDTO;
import com.example.seebook.domain.profile.dto.request.ProfileReviewListRequestDTO;
import com.example.seebook.domain.profile.dto.response.JoinResponseDTO;
import com.example.seebook.domain.profile.service.ProfileService;
import com.example.seebook.domain.user.service.UserService;
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
    private final UserService userService;


    @PostMapping("/review-list")
    public void getProfileReviewList(@Valid @RequestBody ProfileReviewListRequestDTO profileReviewListRequestDTO) {

    }

    @PostMapping("/join")
    public ResponseEntity<JoinResponseDTO> joinProfile(@Valid @RequestBody JoinRequestDTO joinRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(profileService.joinProfile(joinRequestDTO.getUserId()));
    }

    @PostMapping("/image-change")//RequestParam는 쿼리매개변수도 관여하지만 폼데이터를 핸들링할때도 사용
    public ResponseEntity<?> changeImage(@RequestParam("userId") Long userId,
                                         @RequestParam("file") MultipartFile file) {
        profileService.changeImage(userId, file);
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
        userService.changeNickname(changeNicknameRequestDTO.getUserId(), changeNicknameRequestDTO.getNickname());
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}

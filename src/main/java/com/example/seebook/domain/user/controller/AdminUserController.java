package com.example.seebook.domain.user.controller;

import com.example.seebook.domain.profile.service.ProfileService;
import com.example.seebook.domain.review.service.ReviewService;
import com.example.seebook.domain.suspend.service.SuspendService;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.user.dto.requset.AdminUserDeleteRequestDTO;
import com.example.seebook.domain.user.dto.requset.AdminUserModifyRequestDTO;
import com.example.seebook.domain.user.dto.response.AdminUserDetailResponseDTO;
import com.example.seebook.domain.user.dto.response.AdminUserListResponseDTO;
import com.example.seebook.domain.user.service.AdminUserService;
import com.example.seebook.domain.user.service.UserService;
import com.example.seebook.domain.wishlist.service.WishlistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/user")
public class AdminUserController {
    private final AdminUserService adminUserService;
    private final ProfileService profileService;
    private final SuspendService suspendService;

    private final UserService userService;
    private final WishlistService wishlistService;
    private final ReviewService reviewService;


    @GetMapping("/list")
    public ResponseEntity<AdminUserListResponseDTO> getUserList(@RequestParam("page") int page,
                                                                @RequestParam(value = "queryType", defaultValue = "all") String  queryType,
                                                                @RequestParam(value = "query", defaultValue = "all") String query) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adminUserService.getUserList(page, queryType, query));
    }

    @GetMapping("/detail")
    public ResponseEntity<AdminUserDetailResponseDTO> getUserDetail(@RequestParam("userId") Long userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adminUserService.getUserDetail(userId));
    }

    @PutMapping("/modify")
    public ResponseEntity<?> modifyUser(@Valid @RequestBody AdminUserModifyRequestDTO adminUserModifyRequestDTO) {
        if (adminUserModifyRequestDTO.isResetProfileImage()) {
            profileService.resetDefaultProfileImage(adminUserModifyRequestDTO.getUserId());
        }
        if (adminUserModifyRequestDTO.getEndDate() != null) {
            suspendService.changeSuspendByAdminModify(adminUserModifyRequestDTO.getUserId(), adminUserModifyRequestDTO.getEndDate());
        }
        adminUserService.modifyUser(adminUserModifyRequestDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody AdminUserDeleteRequestDTO adminUserDeleteRequestDTO) {
//        연관관계를 많이 맺어놔서 삭제하면 자식들이 삭제가 안돼서 섞여버려서 처리해야함
// 리포트가 핵심인데 만약 해당사람이 신고를 당한게 아닌 다른 사람도 신고를 했는데 적당했으면 그 리뷰들도 삭제해야해서 복잡해짐
//        for (Long userId: adminUserDeleteRequestDTO.getUserId()) {
//            User user = userService.findById(userId);
//            wishlistService.deleteWishlistByUser(user);
//            reviewService.deleteReviewByUser(user);
//            suspendService.deleteById(userId);
//            profileService.deleteProfile(userId);
//
//        }
//        adminUserService.deleteUser(adminUserDeleteRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}

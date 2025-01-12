package com.example.seebook.domain.user.controller;

import com.example.seebook.domain.level.service.LevelService;
import com.example.seebook.domain.profile.service.ProfileService;
import com.example.seebook.domain.report.service.ReportService;
import com.example.seebook.domain.review.service.ReviewService;
import com.example.seebook.domain.support.service.SupportService;
import com.example.seebook.domain.suspend.service.SuspendService;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.user.dto.requset.AdminUserDeleteRequestDTO;
import com.example.seebook.domain.user.dto.requset.AdminUserModifyRequestDTO;
import com.example.seebook.domain.user.dto.response.AdminUserDetailResponseDTO;
import com.example.seebook.domain.user.dto.response.AdminUserListResponseDTO;
import com.example.seebook.domain.user.service.AdminUserService;
import com.example.seebook.domain.user.service.UserService;
import com.example.seebook.domain.wishlist.service.WishlistService;
import com.example.seebook.global.exception.UserException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/user")
@Slf4j
public class AdminUserController {
    private final AdminUserService adminUserService;
    private final ProfileService profileService;
    private final SuspendService suspendService;
    private final LevelService levelService;
    private final ReportService reportService;
    private final SupportService supportService;
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

        for (Long userId : adminUserDeleteRequestDTO.getUserId()) {
            User user = null;
            try {
                user = userService.findById(userId);
            } catch (UserException.NotFoundUserException e) {
                log.warn("User with ID {} not found. Skipping user deletion.", userId);
                continue;
            }

            try {
                wishlistService.deleteWishlistByUser(user);
            } catch (UserException.NotFoundUserException e) {
                log.warn("Wishlist for user ID {} not found. Skipping wishlist deletion.", userId);
            }
            try {
                reviewService.deleteReviewByUser(user);
            } catch (UserException.NotFoundUserException e) {
                log.warn("Review for user ID {} not found. Skipping review deletion.", userId);
            }

            try {
                levelService.deleteLevel(userId);
            } catch (UserException.NotFoundUserException e) {
                log.warn("Level for user ID {} not found. Skipping level deletion.", userId);
            }

            try {
                profileService.deleteProfile(userId);
            } catch (UserException.NotFoundUserException e) {
                log.warn("Profile for user ID {} not found. Skipping profile deletion.", userId);
            }

            try {
                reportService.deleteReport(user);
            } catch (UserException.NotFoundUserException e) {
                log.warn("Report for user ID {} not found. Skipping report deletion.", userId);
            }

            try {
                suspendService.deleteById(userId);
            } catch (UserException.NotFoundUserException e) {
                log.warn("Suspension for user ID {} not found. Skipping suspension deletion.", userId);
            }

            try {
                supportService.deleteSupportByUser(user);
            } catch (UserException.NotFoundUserException e) {
                log.warn("Support for user ID {} not found. Skipping support deletion.", userId);
            }
        }

        adminUserService.deleteUser(adminUserDeleteRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


}

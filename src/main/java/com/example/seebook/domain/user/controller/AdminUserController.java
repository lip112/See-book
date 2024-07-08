package com.example.seebook.domain.user.controller;

import com.example.seebook.domain.profile.service.ProfileService;
import com.example.seebook.domain.suspend.service.SuspendService;
import com.example.seebook.domain.user.dto.requset.AdminUserModifyRequestDTO;
import com.example.seebook.domain.user.dto.response.AdminUserDetailResponseDTO;
import com.example.seebook.domain.user.dto.response.AdminUserListResponseDTO;
import com.example.seebook.domain.user.service.AdminUserService;
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
}

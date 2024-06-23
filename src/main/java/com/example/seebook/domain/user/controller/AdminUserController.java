package com.example.seebook.domain.user.controller;

import com.example.seebook.domain.user.dto.requset.AdminUserDetailRequestDTO;
import com.example.seebook.domain.user.dto.requset.AdminUserModifyRequestDTO;
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

    @GetMapping("/list")
    public ResponseEntity<AdminUserListResponseDTO> getUserList(@RequestParam("page") int page,
                                                                @RequestParam(value = "QueryType", defaultValue = "all") String  QueryType,
                                                                @RequestParam(value = "Query", defaultValue = "all") String Query) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adminUserService.getUserList(page, QueryType, Query));
    }

    @PostMapping("/detail")
    public ResponseEntity<?> getUserDetail(@Valid @RequestBody AdminUserDetailRequestDTO adminUserDetailRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adminUserService.getUserDetail(adminUserDetailRequestDTO));
    }

    @PutMapping("/modify")
    public ResponseEntity<?> modifyUser(AdminUserModifyRequestDTO adminUserModifyRequestDTO) {
        adminUserModifyRequestDTO.isResetPassword();
        adminUserModifyRequestDTO.isResetProfileImage();
        adminUserService.modifyUser(adminUserModifyRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }


}

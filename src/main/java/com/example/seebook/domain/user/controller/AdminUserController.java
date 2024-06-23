package com.example.seebook.domain.user.controller;

import com.example.seebook.domain.user.dto.requset.AdminUserDetailRequestDTO;
import com.example.seebook.domain.user.dto.response.AdminUserListResponseDTO;
import com.example.seebook.domain.user.service.AdminUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
        return ResponseEntity.ok(adminUserService.getUserList(page, QueryType, Query));
    }

    @PostMapping("/detail")
    public ResponseEntity<?> getUserDetail(@Valid @RequestBody AdminUserDetailRequestDTO adminUserDetailRequestDTO) {
        return ResponseEntity.ok(adminUserService.getUserDetail(adminUserDetailRequestDTO));
    }


}

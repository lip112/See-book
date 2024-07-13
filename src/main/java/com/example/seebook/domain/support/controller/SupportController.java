package com.example.seebook.domain.support.controller;

import com.example.seebook.domain.support.domain.SupportType;
import com.example.seebook.domain.support.dto.request.SupportRequestDTO;
import com.example.seebook.domain.support.dto.response.SupportDetailResponseDTO;
import com.example.seebook.domain.support.dto.response.SupportListResponseDTO;
import com.example.seebook.domain.support.service.SupportService;
import com.example.seebook.domain.user.service.UserService;
import com.example.seebook.global.jwt.UserAuthorizationUtil;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/support")
public class SupportController {
    private final SupportService supportService;
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<SupportListResponseDTO> getSupportList(@RequestParam("page") int page) {
        Long userId = UserAuthorizationUtil.getLoginUserId();
        return ResponseEntity.ok(supportService.getSupportList(page, userId));
    }

    @GetMapping("/detail")
    public ResponseEntity<SupportDetailResponseDTO> getSupportDetail (@RequestParam("supportId") Long supportId) {
        return ResponseEntity.ok(supportService.getSupportDetail(supportId));
    }

    @PostMapping("/request")
    public ResponseEntity<Void> requestSupport(@Valid @RequestBody SupportRequestDTO requestDTO) {
        Long userId = UserAuthorizationUtil.getLoginUserId();
        supportService.requestSupport(requestDTO.getSupportType(), requestDTO.getContent(), userService.findById(userId));
        return ResponseEntity.ok().build();
    }


}

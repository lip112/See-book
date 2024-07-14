package com.example.seebook.domain.support.controller;

import com.example.seebook.domain.support.domain.SupportType;
import com.example.seebook.domain.support.dto.request.AdminDeleteSupportRequestDTO;
import com.example.seebook.domain.support.dto.request.AdminSupportRequestDTO;
import com.example.seebook.domain.support.dto.response.AdminSupportDetailResponseDTO;
import com.example.seebook.domain.support.dto.response.AdminSupportListResponseDTO;
import com.example.seebook.domain.support.service.AdminSupportService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/support")
public class AdminSupportController {
    private final AdminSupportService adminSupportService;

    @GetMapping("/list")
    public ResponseEntity<AdminSupportListResponseDTO> getSupportList(@RequestParam("page") int page,
                                                                      @RequestParam("query") String query,
                                                                      @RequestParam("queryType") String queryType) {
        return ResponseEntity.ok(adminSupportService.getSupportList(page, query, queryType));
    }

    @GetMapping("/detail")
    public ResponseEntity<AdminSupportDetailResponseDTO> getSupportDetail(@RequestParam("supportId") Long supportId) {
        return ResponseEntity.ok(adminSupportService.getSupportDetail(supportId));
    }

    @PostMapping("/process")
    public ResponseEntity<Void> processSupport(@Valid @RequestBody AdminSupportRequestDTO requestDTO) {
        adminSupportService.processSupport(requestDTO.getSupportId(), requestDTO.getReplyContent());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteSupport(@RequestBody AdminDeleteSupportRequestDTO requestDTO) {
        adminSupportService.deleteSupport(requestDTO);
        return ResponseEntity.ok().build();
    }
}

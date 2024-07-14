package com.example.seebook.domain.event.controller;

import com.example.seebook.domain.event.dto.EventDTO;
import com.example.seebook.domain.event.dto.request.AdminEventDeleteRequestDTO;
import com.example.seebook.domain.event.dto.request.AdminEventModifyRequestDTO;
import com.example.seebook.domain.event.dto.response.AdminEventDetailResponseDTO;
import com.example.seebook.domain.event.dto.response.AdminEventListResponseDTO;
import com.example.seebook.domain.event.service.AdminEventService;
import com.example.seebook.global.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.http.POST;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/event")
public class AdminEventController {
    private final AdminEventService adminEventService;
    private final S3Uploader s3Uploader;

    @GetMapping("/list")
    public ResponseEntity<AdminEventListResponseDTO> getAdminEventList(int page, String query, String queryType) {
        return ResponseEntity
                .ok()
                .body(adminEventService.getAdminEventList(page, query, queryType));
    }

    @GetMapping("/detail")
    public ResponseEntity<AdminEventDetailResponseDTO> getAdminEventDetail(@RequestParam("eventId")Long eventId) {
        return ResponseEntity
                .ok()
                .body(adminEventService.getAdminEventDetail(eventId));
    }

    @PutMapping("/modify")
    public ResponseEntity<?> modifyEvent(@RequestPart("event") EventDTO modifyEventDTO,
                                         @RequestPart("isEarlyStopped") boolean isEarlyStopped,
                                         @RequestPart(value = "file", required = false) MultipartFile file){
        modifyEventDTO.isEarlyStopped(isEarlyStopped);

        if (file != null && !file.isEmpty()) {
            String uploadUrl = s3Uploader.upload(file);
            modifyEventDTO.changeImageUrl(uploadUrl);
        }

        adminEventService.modifyEvent(modifyEventDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerEvent(@RequestPart("event") EventDTO eventDTO,
                                           @RequestPart("file") MultipartFile file) {
        String uploadUrl = s3Uploader.upload(file);
        eventDTO.changeImageUrl(uploadUrl);

        adminEventService.registerEvent(eventDTO);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEvent(@RequestBody AdminEventDeleteRequestDTO requestDTO) {
        adminEventService.deleteEvent(requestDTO);
        return ResponseEntity
                .ok()
                .build();
    }
}

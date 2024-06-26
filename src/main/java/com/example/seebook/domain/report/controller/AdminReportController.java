package com.example.seebook.domain.report.controller;

import com.example.seebook.domain.profile.service.ProfileService;
import com.example.seebook.domain.report.dto.request.AdminReportDetailRequestDTO;
import com.example.seebook.domain.report.dto.request.AdminReportProcessRequestDTO;
import com.example.seebook.domain.report.dto.response.AdminReportDetailResponseDTO;
import com.example.seebook.domain.report.dto.response.AdminReportListResponseDTO;
import com.example.seebook.domain.report.service.AdminReportService;
import com.example.seebook.domain.review.service.ReviewService;
import com.example.seebook.domain.suspend.service.SuspendService;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/report")
public class AdminReportController {
    private final AdminReportService adminReportService;
    private final ReviewService reviewService;
    private final ProfileService profileService;
    private final SuspendService suspendService;
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<AdminReportListResponseDTO> getReportList(@RequestParam("page") int page,
                                                                    @RequestParam("queryType") String queryType,
                                                                    @RequestParam("query") String query) {
        return ResponseEntity.ok()
                .body(adminReportService.getReportList(page, queryType, query));

    }

    @PostMapping("/detail")
    public ResponseEntity<AdminReportDetailResponseDTO> getReportDetail(@RequestBody AdminReportDetailRequestDTO requestDTO) {
        return ResponseEntity.ok()
                .body(adminReportService.getReportDetail(requestDTO.getReportId()));
    }

    @PostMapping("/process")
    public ResponseEntity<Void> processReport(@RequestBody AdminReportProcessRequestDTO requestDTO) {
        if (requestDTO.isDeleteReview()) {
            User user = userService.findById(requestDTO.getReviewId());
            reviewService.deleteReview(requestDTO.getReviewId(), user);
        }
        if (requestDTO.isResetNickname()) {
            userService.changeNickname(requestDTO.getReportedId(), "꿀먹은오둥이" + new Random(90000) + 10000);
        }
        if (requestDTO.isResetProfileImage()) {
            profileService.resetDefaultImage(requestDTO.getReportedId());
        }
        if (requestDTO.getSuspensionPeriod() > 0) {
            suspendService.changeSuspensionPeriod(requestDTO.getReportedId(), requestDTO.getSuspensionPeriod(), requestDTO.getReportType());
        }
        adminReportService.processReport(requestDTO);
        return ResponseEntity.ok().build();
    }
}

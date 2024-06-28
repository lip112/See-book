package com.example.seebook.domain.report.repository;


import com.example.seebook.domain.report.dto.ReportDTO;
import com.example.seebook.domain.report.dto.response.AdminReportDetailResponseDTO;
import com.example.seebook.domain.report.dto.response.AdminReportListResponseDTO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.seebook.domain.profile.domain.QProfile.profile;
import static com.example.seebook.domain.report.domain.QReport.report;
import static com.example.seebook.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class ReportRepositoryCustomImpl implements ReportRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public AdminReportListResponseDTO getAdminReportList(int offset, int limit, String query, String queryType) {
        List<ReportDTO> list = queryFactory
                .select(report.reportId, report.reporterId.userId, report.reporterId.nickname, report.reportedId.userId, report.reportedId.nickname,
                        report.reportType, report.reportDate, report.description, report.isProcessed)
                .from(report)
                .where(getQuery(query, queryType))
                .offset(offset)
                .limit(limit)
                .fetch()
                .stream()
                .map(tuple -> ReportDTO.builder()
                        .reportId(tuple.get(report.reportId))
                        .reporterId(tuple.get(report.reporterId.userId))
                        .reporterNickname(tuple.get(report.reporterId.nickname))
                        .reportedId(tuple.get(report.reportedId.userId))
                        .reportedNickname(tuple.get(report.reportedId.nickname))
                        .reportType(tuple.get(report.reportType))
                        .reportDate(tuple.get(report.reportDate).toString())
                        .description(tuple.get(report.description))
                        .isProcessed(tuple.get(report.isProcessed))
                        .build())
                .toList();
        Long count = queryFactory
                .select(report.count())
                .from(report)
                .where(getQuery(query, queryType))
                .fetchOne();
        return AdminReportListResponseDTO.builder()
                .report(list)
                .totalReportCount(count)
                .endPage(count / 10 + 1)
                .build();
    }

    private BooleanExpression getQuery(String query, String queryType) {
        if (!queryType.equals("all")) {
            if (queryType.equals("reporter")) {
                return  report.reporterId.userId.eq(
                        JPAExpressions
                                .select(user.userId)
                                .from(user)
                                .where(user.nickname.eq(query))
                );
            } else if (queryType.equals("reported")) {
                return report.reportedId.userId.eq(
                        JPAExpressions
                                .select(user.userId)
                                .from(user)
                                .where(user.nickname.eq(query))
                );
            } else if (queryType.equals("reportType")) {
                return report.reportType.eq(query);
            } else if (queryType.equals("reportDate")) {
                return report.reportDate.eq(LocalDateTime.parse(query));
            }
        }
        return null;
    }

    @Override
    public AdminReportDetailResponseDTO getAdminReportDetail(Long reportId) {
        Tuple tuple = queryFactory
                .select(
                        report.reportId,
                        report.reporterId.nickname,
                        report.reportedId.userId,
                        report.reportedId.nickname,
                        report.reportType,
                        report.reportDate,
                        report.description,
                        report.reviewId,
                        profile.imageUrl,
                        report.reviewId.starRating,
                        report.reviewId.content)
                .from(report)
                .leftJoin(profile).on(report.reportedId.userId.eq(profile.userId))
                .where(report.reportId.eq(reportId))
                .fetchOne();


        return AdminReportDetailResponseDTO.builder()
                .reportId(tuple.get(report.reportId))
                .reporterNickname(tuple.get(report.reporterId.nickname))
                .reportedId(tuple.get(report.reportedId.userId))
                .reportedNickname(tuple.get(report.reportedId.nickname))
                .reportType(tuple.get(report.reportType))
                .reportDate(tuple.get(report.reportDate).toString())
                .description(tuple.get(report.description))
                .reviewId(tuple.get(report.reviewId).getReviewId())
                .profileIamge(tuple.get(profile.imageUrl))
                .starRating(tuple.get(report.reviewId.starRating))
                .content(tuple.get(report.reviewId.content))
                .build();
    }
}

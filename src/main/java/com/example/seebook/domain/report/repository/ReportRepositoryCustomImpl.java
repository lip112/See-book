package com.example.seebook.domain.report.repository;


import com.example.seebook.domain.report.dto.ReportDTO;
import com.example.seebook.domain.report.dto.response.AdminReportDetailResponseDTO;
import com.example.seebook.domain.report.dto.response.AdminReportListResponseDTO;
import com.example.seebook.global.exception.ReportException;
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
    public AdminReportListResponseDTO getAdminReportList(int offset, int limit, String queryType, String query) {
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
                        .reportDate(tuple.get(report.reportDate))
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
                .endPage(count / 5 + 1)
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
                        profile.imageUrl,
                        report.reviewId.reviewId,
                        report.reviewId.content)
                .from(report)
                .leftJoin(profile).on(profile.userId.eq(report.reportedId.userId))
                .where(report.reportId.eq(reportId))
                .fetchOne();

        if (tuple == null) {
            throw new ReportException.NotFoundReportIdException();
        }

        return AdminReportDetailResponseDTO.builder()
                .reportId(tuple.get(report.reportId))
                .reporterNickname(tuple.get(report.reporterId.nickname))
                .reportedId(tuple.get(report.reportedId.userId))
                .reportedNickname(tuple.get(report.reportedId.nickname))
                .reportType(tuple.get(report.reportType))
                .reportDate(tuple.get(report.reportDate))
                .description(tuple.get(report.description))
                .reviewId(tuple.get(report.reviewId.reviewId))
                .profileIamge(tuple.get(profile.imageUrl))
                .content(tuple.get(report.reviewId.content))
                .build();
    }
}

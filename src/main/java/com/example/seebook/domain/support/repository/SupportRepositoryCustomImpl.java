package com.example.seebook.domain.support.repository;

import com.example.seebook.domain.support.dto.SupportDTO;
import com.example.seebook.domain.support.dto.response.AdminSupportListResponseDTO;
import com.example.seebook.domain.support.dto.response.SupportListResponseDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.seebook.domain.support.domain.QSupport.support;
import static com.example.seebook.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class SupportRepositoryCustomImpl implements SupportRepositoryCustom{
    private final JPAQueryFactory queryFactory;


    @Override
    public SupportListResponseDTO getList(Long userId, int offset, int limit) {
        List<SupportDTO> list = queryFactory
                .selectFrom(support)
                .where(support.userId.userId.eq(userId))
                .orderBy(support.supportId.desc())
                .offset(offset)
                .limit(limit)
                .fetch()
                .stream()
                .map(support ->
                        SupportDTO.builder()
                                .supportId(support.getSupportId())
                                .supportType(support.getSupportType())
                                .content(support.getContent())
                                .isProcessed(support.isProcessed())
                                .requestDate(support.getRequestDate())
                                .build()
                )
                .toList();

        long totalSupportCount = queryFactory
                .selectFrom(support)
                .fetch()
                .size();
        return SupportListResponseDTO.builder()
                .totalSupportCount(totalSupportCount)
                .endPage(totalSupportCount /10 + 1)
                .support(list)
                .build();
    }

    @Override
    public AdminSupportListResponseDTO getAdminList(int offset, int limit) {
        List<SupportDTO> list = queryFactory
                .select(support.supportId, support.supportType, support.isProcessed, support.requestDate,
                        user.email, user.nickname, user.name)
                .from(support)
                .leftJoin(user).on(support.userId.eq(user))
                .orderBy(support.supportId.desc())
                .offset(offset)
                .limit(limit)
                .fetch()
                .stream()
                .map(tuple ->
                        SupportDTO.builder()
                                .supportId(tuple.get(support.supportId))
                                .supportType(tuple.get(support.supportType))
                                .isProcessed(tuple.get(support.isProcessed))
                                .requestDate(tuple.get(support.requestDate))
                                .email(tuple.get(user.email))
                                .name(tuple.get(user.name))
                                .nickname(tuple.get(user.nickname))
                                .build()
                )
                .toList();
        Long totalSupportCount = queryFactory
                .select(support.count())
                .from(support)
                .fetchOne();
        return AdminSupportListResponseDTO.builder()
                .totalSupportCount(totalSupportCount)
                .endPage(totalSupportCount / 10 + 1)
                .support(list)
                .build();
    }
}

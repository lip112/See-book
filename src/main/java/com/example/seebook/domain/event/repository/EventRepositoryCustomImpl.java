package com.example.seebook.domain.event.repository;


import com.example.seebook.domain.event.dto.AdminEventListDTO;
import com.example.seebook.domain.event.dto.response.AdminEventDetailResponseDTO;
import com.example.seebook.domain.event.dto.response.AdminEventListResponseDTO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.seebook.domain.event.domain.QEvent.event;

@RequiredArgsConstructor
public class EventRepositoryCustomImpl implements EventRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public AdminEventListResponseDTO getAdminEventList(int offset, int limit, String query, String queryType) {

        List<AdminEventListDTO> list = jpaQueryFactory
                .select(event.eventId, event.title, event.startDate, event.endDate, event.imageLink)
                .from(event)
                .where(eqQueryType(queryType, query))
                .offset(offset)
                .limit(limit)
                .fetch()
                .stream()
                .map(tuple -> AdminEventListDTO.builder()
                        .eventId(tuple.get(event.eventId))
                        .title(tuple.get(event.title))
                        .startDate(tuple.get(event.startDate).toString())
                        .endDate(tuple.get(event.endDate).toString())
                        .imageLink(tuple.get(event.imageLink))
                        .build())
                .toList();
        Long count = jpaQueryFactory
                .select(event.count())
                .from(event)
                .where(eqQueryType(queryType, query))
                .fetchCount();
        return AdminEventListResponseDTO.builder()
                .totalEventCount(count)
                .endPage(count /10 +1)
                .event(list)
                .build();
    }

    private BooleanExpression eqQueryType(String queryType, String query) {
        if (queryType.equals("title")) {
            return event.title.contains(query);
        }else {
            return null;
        }
    }


    @Override
    public AdminEventDetailResponseDTO getAdminEventDetail(Long eventId) {
        Tuple tuple = jpaQueryFactory
                .select(event.eventId, event.title, event.startDate, event.endDate, event.imageLink)
                .from(event)
                .where(event.eventId.eq(eventId))
                .fetchOne();

        return AdminEventDetailResponseDTO.builder()
                .eventId(tuple.get(event.eventId))
                .title(tuple.get(event.title))
                .startDate(tuple.get(event.startDate).toString())
                .endDate(tuple.get(event.endDate).toString())
                .imageLink(tuple.get(event.imageLink))
                .build();
    }
}

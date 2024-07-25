package com.example.seebook.domain.profile.repository;

import com.example.seebook.domain.profile.dto.AnotherProfileReviewDTO;
import com.example.seebook.domain.profile.dto.request.ProfileReviewListRequestDTO;
import com.example.seebook.domain.profile.dto.response.JoinResponseDTO;
import com.example.seebook.domain.profile.dto.response.ProfileReviewListResponseDTO;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.example.seebook.domain.book.domain.QBook.book;
import static com.example.seebook.domain.level.domain.QLevelInfo.levelInfo;
import static com.example.seebook.domain.profile.domain.QProfile.profile;
import static com.example.seebook.domain.review.domain.QReview.review;
import static com.example.seebook.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class ProfileRepositoryCustomImpl implements ProfileRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public ProfileReviewListResponseDTO getProfileWithReviewListDTO(Long userId, int offset, int limit){
        Tuple prifileTuple = queryFactory
                .select(profile.imageUrl, user.nickname, levelInfo.level, levelInfo.levelCount)
                .from(profile)
                .innerJoin(user).on(user.userId.eq(userId))
                .innerJoin(levelInfo).on(levelInfo.userId.eq(userId))
                .where(profile.userId.eq(userId))
                .fetchOne();

        List<AnotherProfileReviewDTO> list = queryFactory
                .select(review.book.bookId, review.starRating, review.content, review.createdDate,
                        book.isbn13, book.imageLink, book.title)
                .from(review)
                //해당 책의 모든 리뷰들을 가져와서 평균을 구해야 하는데 모름 따로 구성해야 하는가 서브쿼리 이용?
                .innerJoin(book).on(book.bookId.eq(review.book.bookId))
                .where(review.user.userId.eq(userId))
                .groupBy(review.book.bookId)
                .offset(offset)
                .limit(limit)
                .fetch()
                .stream()
                .map(tuple -> AnotherProfileReviewDTO.builder()
                        .isbn13(tuple.get(book.isbn13))
                        .imageLink(tuple.get(book.imageLink))
                        .title(tuple.get(book.title))
                        .content(tuple.get(review.content))
                        .starRating(tuple.get(review.starRating))
                        .createdDate(tuple.get(review.createdDate))
                        .build())
                .toList();

        Long reviewCount = queryFactory
                .select(review.count())
                .from(review)
                .where(review.user.userId.eq(userId))
                .fetchOne();

        return ProfileReviewListResponseDTO.builder()
                .profileImage(prifileTuple.get(profile.imageUrl))
                .nickname(prifileTuple.get(user.nickname))
                .level(prifileTuple.get(levelInfo.level))
                .levelCount(prifileTuple.get(levelInfo.levelCount))
                .totalReviewCount(reviewCount)
                .endPage(reviewCount / 10 + 1)
                .reviewList(list)
                .build();
    }

    @Override
    public JoinResponseDTO joinProfile(Long userId) {
        Tuple tuple = queryFactory
                .select(profile.imageUrl, user.nickname, user.email, levelInfo.level, levelInfo.levelCount)
                .from(profile)
                .innerJoin(user).on(user.userId.eq(userId))
                .innerJoin(levelInfo).on(levelInfo.userId.eq(userId))
                .where(profile.userId.eq(userId))
                .fetchOne();

        return JoinResponseDTO.builder()
                .profileImage(tuple.get(profile.imageUrl))
                .nickname(tuple.get(user.nickname))
                .email(tuple.get(user.email))
                .level(tuple.get(levelInfo.level))
                .levelCount(tuple.get(levelInfo.levelCount))
                .build();

    }
}

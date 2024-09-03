package com.example.seebook.domain.profile.repository;

import com.example.seebook.domain.profile.dto.AnotherProfileReviewDTO;
import com.example.seebook.domain.profile.dto.request.ProfileReviewListRequestDTO;
import com.example.seebook.domain.profile.dto.response.JoinResponseDTO;
import com.example.seebook.domain.profile.dto.response.ProfileReviewListResponseDTO;
import com.example.seebook.global.exception.ReviewException;
import com.example.seebook.global.exception.UserException;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.validation.constraints.Null;
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

        if (prifileTuple == null) {
            throw new UserException.NotFoundUserException();
        }

        List<AnotherProfileReviewDTO> list = queryFactory
                .select(Projections.constructor(
                        AnotherProfileReviewDTO.class,
                        book.isbn13,
                        book.imageLink,
                        book.title,
                        JPAExpressions
                                .select(review.starRating.avg())
                                .from(review)
                                .where(review.book.bookId.eq(book.bookId))
                                .groupBy(review.book.bookId),
                        review.content,
                        review.starRating,
                        review.createdDate
                ))
                .from(review)
                .innerJoin(book).on(book.bookId.eq(review.book.bookId))
                .where(review.user.userId.eq(userId))
                .offset(offset)
                .limit(limit)
                .fetch();

        Long reviewCount = queryFactory
                .select(review.count())
                .from(review)
                .where(review.user.userId.eq(userId))
                .fetchOne();
        if (reviewCount == 0) {
            throw new ReviewException.NotFoundReviewException();
        }

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

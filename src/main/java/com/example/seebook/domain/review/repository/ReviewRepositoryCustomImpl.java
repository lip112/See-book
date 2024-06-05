package com.example.seebook.domain.review.repository;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.level.domain.QLevelInfo;
import com.example.seebook.domain.review.dto.ReviewDTO;
import com.example.seebook.domain.review.dto.response.BookInReviewListResponseDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.seebook.domain.level.domain.QLevelInfo.levelInfo;
import static com.example.seebook.domain.profile.domain.QProfile.profile;
import static com.example.seebook.domain.review.domain.QReview.review;
import static com.example.seebook.domain.user.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public BookInReviewListResponseDTO getReviewList(Book book, int page, int offset, int limit) {

        List<ReviewDTO> reviewDTOList = queryFactory
                .select(
                        review.reviewId,
                        review.starRating,
                        review.user,
                        review.content,
                        review.nickname,
                        levelInfo.level,
                        profile.imageUrl
                )
                .from(review)
                .innerJoin(levelInfo).on(user.userId.eq(levelInfo.userId))
                .innerJoin(profile).on(user.userId.eq(profile.userId))
                .where(review.book.bookId.eq(book.getBookId()))
                .offset(offset)
                .limit(limit)
                .orderBy(review.reviewId.desc())
                .fetch()
                .stream()
                .map(tuple -> ReviewDTO.builder()
                        .reviewId(tuple.get(review.reviewId)) // 타입 변환
                        .userId(tuple.get(review.user.userId))
                        .profileImage(tuple.get(profile.imageUrl))
                        .nickname(tuple.get(review.nickname))
                        .level(tuple.get(levelInfo.level))
                        .starRating(tuple.get(review.starRating))
                        .content(tuple.get(review.content))
                        .build()
                )
                .collect(Collectors.toList());

        long total = queryFactory
                .select(review.count())
                .from(review)
                .where(review.book.eq(book))
                .fetchOne();

        System.out.println(total);
        //일단 다 만들었는데 테스트를 못 한다. 우선 댓글 부분 다 만들고 나서 북 마저 완성하기
        return null;
    }
}

package com.example.seebook.domain.review.repository;

import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.review.dto.ProfileReviewDTO;
import com.example.seebook.domain.review.dto.ReviewDTO;
import com.example.seebook.domain.book.dto.response.BookInReviewListResponseDTO;
import com.example.seebook.domain.review.dto.request.ProfileReviewRequestDTO;
import com.example.seebook.domain.review.dto.response.ProfileReviewResponseDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.seebook.domain.book.domain.QBook.book;
import static com.example.seebook.domain.level.domain.QLevelInfo.levelInfo;
import static com.example.seebook.domain.profile.domain.QProfile.profile;
import static com.example.seebook.domain.review.domain.QReview.review;
import static com.example.seebook.domain.user.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public BookInReviewListResponseDTO getBookInReviewList(BookDTO bookDTO, int page, int offset, int limit) {

        List<ReviewDTO> reviewDTOList = queryFactory
                .select(
                        review.reviewId,
                        review.user.userId,
                        review.starRating,
                        profile.imageUrl,
                        review.nickname,
                        review.content,
                        levelInfo.level
                )
                .from(review)
                .innerJoin(levelInfo).on(user.userId.eq(levelInfo.userId))
                .innerJoin(profile).on(user.userId.eq(profile.userId))
                .where(review.book.bookId.eq(bookDTO.getBookId()))
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

        Long reviewCount = queryFactory
                .select(review.count())
                .from(review)
                .where(review.book.bookId.eq(book.bookId))
                .fetchOne();


        System.out.println(reviewCount);
        //일단 다 만들었는데 테스트를 못 한다. 우선 댓글 부분 다 만들고 나서 북 마저 완성하기
        return BookInReviewListResponseDTO.builder()
                .reviewCount(reviewCount)
                .endPage(reviewCount/10 +1)
                .review(reviewDTOList)
                .book(bookDTO)
                .build();
    }

    @Override
    public ProfileReviewResponseDTO getProfileReviewList(Long userId, int offset, int limit) {
        List<ProfileReviewDTO> list = queryFactory
                .select(book.isbn13, book.title, book.imageLink,
                        review.reviewId, review.content, review.starRating)
                .from(review)
                .leftJoin(book).on(book.bookId.eq(review.book.bookId))
                .where(review.user.userId.eq(userId))
                .offset(offset)
                .limit(limit)
                .fetch()
                .stream()
                .map(tuple -> ProfileReviewDTO.builder()
                        .userId(userId)
                        .isbn13(tuple.get(book.isbn13))
                        .title(tuple.get(book.title))
                        .imageLink(tuple.get(book.imageLink))
                        .reviewId(tuple.get(review.reviewId))
                        .content(tuple.get(review.content))
                        .starRating(tuple.get(review.starRating))
                        .build())
                .toList();
        Long reviewCount = queryFactory
                .select(review.count())
                .from(review)
                .where(review.user.userId.eq(userId))
                .fetchOne();

        return ProfileReviewResponseDTO.builder()
                .totalReviewCont(reviewCount)
                .endPage(reviewCount / 10 + 1)
                .review(list)
                .build();
    }
}

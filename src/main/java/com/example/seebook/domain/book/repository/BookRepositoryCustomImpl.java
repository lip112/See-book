package com.example.seebook.domain.book.repository;

import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.book.dto.response.BookListResponseDTO;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.seebook.domain.book.domain.QBook.book;
import static com.example.seebook.domain.review.domain.QReview.review;
import static com.example.seebook.domain.wishlist.domain.QWishlist.wishlist;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public BookListResponseDTO getBooksReviewSummary(BookListResponseDTO bookList) {
        List<Long> bookIds = bookList.getBook().stream()
                .map(BookDTO::getBookId)
                .toList();

        Map<Long, Tuple> results = queryFactory
                .select(book.bookId, book.title, review.starRating.avg(), review.reviewId.count())
                .from(book)
                .leftJoin(review).on(review.book.bookId.eq(book.bookId))
                .where(book.bookId.in(bookIds))
                .groupBy(book.bookId, book.title)
                .fetch()
                //결과를 bookList에 쉽게 매칭하기 위해 Map으로 전환
                .stream()
                .collect(Collectors.toMap(
                        tuple -> tuple.get(book.bookId),
                        tuple -> tuple
                ));
        //sql에서 가져온 값과 일치하는 값에 매칭해서 값을 넣음 O(1)
        for (BookDTO bookDTO : bookList.getBook()) {
            if (results.containsKey(bookDTO.getBookId())){
                bookDTO.addAvgStar(results.get(bookDTO.getBookId()).get(review.starRating.avg()));
                bookDTO.addTotalReviewCount(results.get(bookDTO.getBookId()).get(review.reviewId.count()));
            } else {
                bookDTO.addAvgStar(0);
                bookDTO.addTotalReviewCount(0L);
            }
        }

        return bookList;
    }

    @Override
    public BookDTO getBooksReviewSummary(BookDTO bookDTO) {

        Tuple results = queryFactory
                .select(book.bookId, book.title, review.starRating.avg(), review.reviewId.count(), wishlist.book.bookId.count())
                .from(book)
                .leftJoin(review).on(review.book.bookId.eq(book.bookId))
                .leftJoin(wishlist).on(wishlist.book.bookId.eq(book.bookId))
                .where(book.bookId.eq(bookDTO.getBookId()))
                .fetchOne();

        Double avgStarRating = results.get(review.starRating.avg());
        Long reviewCount = results.get(review.reviewId.count());
        Long wishlistCount = results.get(wishlist.book.bookId.count());

        if (avgStarRating == null) {
            avgStarRating = 0.0;  // 기본값 설정
        }
        if (reviewCount == null) {
            reviewCount = 0L;  // 기본값 설정
        }
        if (wishlistCount == null) {
            wishlistCount = 0L;  // 기본값 설정
        }

        bookDTO.addAvgStar(avgStarRating);
        bookDTO.addTotalReviewCount(reviewCount);
        bookDTO.addWishlistCount(wishlistCount);

        return bookDTO;
    }
}

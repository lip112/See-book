package com.example.seebook.domain.book.repository;

import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.book.dto.response.BookListResponseDTO;
import com.example.seebook.domain.main.dto.response.CategoryResponseDTO;
import com.example.seebook.domain.main.dto.response.JoinMainPageResponseDTO;
import com.example.seebook.domain.main.dto.response.NewBookResponseDTO;
import com.example.seebook.domain.review.domain.Review;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.seebook.domain.book.domain.QBook.book;
import static com.example.seebook.domain.review.domain.QReview.review;
import static com.example.seebook.domain.wishlist.domain.QWishlist.wishlist;
import static com.querydsl.jpa.JPAExpressions.select;

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

    @Override
    public List<JoinMainPageResponseDTO.BookWithReview> getBestBooks() {
        List<Long> bookIds = queryFactory
                .select(book.bookId)
                .from(review)
                .groupBy(review.book)
                .orderBy(review.book.bookId.desc())
                .limit(50)
                .fetch();
        List<JoinMainPageResponseDTO.BookWithReview> list = queryFactory
                .select(book.isbn13, book.title, book.author, book.publisher, book.imageLink
                        , review.starRating.avg(), review.reviewId.count(),
                        review.nickname, review.content)
                .from(book)
                .leftJoin(review).on(review.book.bookId.in(bookIds))
                .where(book.bookId.in(bookIds))
                .groupBy(review.book.bookId)
                .fetch()
                .stream()
                .map(tuple ->
                        JoinMainPageResponseDTO.BookWithReview.builder()
                                .isbn13(tuple.get(book.isbn13))
                                .title(tuple.get(book.title))
                                .author(tuple.get(book.author))
                                .publisher(tuple.get(book.publisher))
                                .imageLink(tuple.get(book.imageLink))
                                .avgStar(tuple.get(review.starRating.avg()))
                                .totalReviewCount(tuple.get(review.reviewId.count()))
                                .nickname(tuple.get(review.nickname))
                                .content(tuple.get(review.content))
                                .build()
                )
                .toList();
        return list;
    }

    @Override
    public CategoryResponseDTO findCategoryBooks(String categoryName, int offset, int limit) {
        List<BookDTO> bookDTOList = queryFactory
                .select(book.title, book.author, book.publisher, book.isbn13, book.imageLink,
                        review.starRating.avg(), review.reviewId.count())
                .from(book)
                .leftJoin(review).on(review.book.bookId.eq(book.bookId))
                .where(book.categoryName.eq(categoryName))
                .groupBy(book.title, book.author, book.publisher, book.isbn13)
                .offset(offset)
                .limit(limit)
                .fetch()
                .stream()
                .map(tuple -> BookDTO.builder()
                        .title(tuple.get(book.title))
                        .author(tuple.get(book.author))
                        .publisher(tuple.get(book.publisher))
                        .isbn13(tuple.get(book.isbn13))
                        .imageLink(tuple.get(book.imageLink))
                        .avgStar(tuple.get(review.starRating.avg()))
                        .totalReviewCount(tuple.get(review.reviewId.count()))
                        .build())
                .collect(Collectors.toList());

        Long categoryCount = queryFactory
                .select(book.count())
                .from(book)
                .where(book.categoryName.eq(categoryName))
                .fetchOne();
        return CategoryResponseDTO.builder()
                .book(bookDTOList)
                .totalCategoryCount(categoryCount)
                .endPage(categoryCount / 10 + 1)
                .build();
    }

    @Override
    public NewBookResponseDTO getNewBooks(int offset, int limit) {
        List<BookDTO> list = queryFactory
                .select(book.bookId, book.isbn13, book.title, book.author, book.publisher, book.imageLink
                        , review.starRating.avg(), review.reviewId.count())
                .from(review)
                .leftJoin(book).on(review.book.bookId.eq(book.bookId))
                .where(review.createdDate.after(LocalDateTime.now().minusDays(30)))
                .orderBy(review.reviewId.desc())
                .groupBy(review.book)
                .offset(offset)
                .limit(limit)
                .fetch()
                .stream()
                .map(tuple ->
                        BookDTO.builder()
                                .bookId(tuple.get(book.bookId))
                                .isbn13(tuple.get(book.isbn13))
                                .title(tuple.get(book.title))
                                .author(tuple.get(book.author))
                                .publisher(tuple.get(book.publisher))
                                .imageLink(tuple.get(book.imageLink))
                                .avgStar(tuple.get(review.starRating.avg()))
                                .totalReviewCount(tuple.get(review.reviewId.count()))
                                .build()
                )
                .toList();

        int totalBookCount = queryFactory
                .selectFrom(review)
                .where(review.createdDate.after(LocalDateTime.now().minusDays(30)))
                .orderBy(review.reviewId.desc())
                .groupBy(review.book)
                .fetch()
                .size();
        int endPage = totalBookCount / 10 + 1;

        return NewBookResponseDTO.builder()
                .totalBookCount((long) totalBookCount)
                .endPage(endPage)
                .book(list)
                .build();
    }
}

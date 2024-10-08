package com.example.seebook.domain.wishlist.repository;

import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.wishlist.dto.response.GetWishlistResponseDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.seebook.domain.book.domain.QBook.book;
import static com.example.seebook.domain.review.domain.QReview.review;
import static com.example.seebook.domain.wishlist.domain.QWishlist.wishlist;

@RequiredArgsConstructor
public class WishlistRepositoryCustomImpl implements WishlistRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public GetWishlistResponseDTO getWIshlist(Long userId, int offset, int limit) {

        //해당 userId에 10개씩 bookId를 가져옴
        List<Long> bookIds = queryFactory
                .select(wishlist.book.bookId)
                .from(wishlist)
                .where(wishlist.user.userId.eq(userId))
                .offset(offset)
                .limit(limit)
                .fetch();

        List<BookDTO> bookDTOList = queryFactory
                .select(book.bookId, book.title, book.author, book.publisher, book.isbn13, book.imageLink,
                        review.starRating.avg(), review.reviewId.count())
                .from(book)
                .leftJoin(review).on(review.book.bookId.eq(book.bookId))
                .where(book.bookId.in(bookIds))
                .groupBy(book.bookId, book.title, book.author, book.publisher, book.isbn13)
                .fetch()
                .stream()
                .map(tuple -> BookDTO.builder()
                        .bookId(tuple.get(book.bookId))
                        .imageLink(tuple.get(book.imageLink))
                        .title(tuple.get(book.title))
                        .author(tuple.get(book.author))
                        .publisher(tuple.get(book.publisher))
                        .isbn13(tuple.get(book.isbn13))
                        .avgStar(tuple.get(review.starRating.avg()))
                        .totalReviewCount(tuple.get(review.reviewId.count()))
                        .build())
                .collect(Collectors.toList());

        Long totalWishlistCount = queryFactory
                .select(wishlist.count())
                .from(wishlist)
                .where(wishlist.user.userId.eq(userId))
                .fetchOne();

        return GetWishlistResponseDTO.builder()
                .book(bookDTOList)
                .totalWishlistCount(totalWishlistCount)
                .endPage(totalWishlistCount / 10 + 1)
                .build();
    }
}

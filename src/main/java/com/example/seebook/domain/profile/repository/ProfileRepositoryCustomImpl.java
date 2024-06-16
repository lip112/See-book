package com.example.seebook.domain.profile.repository;

import com.example.seebook.domain.profile.dto.ProfileReviewDTO;
import com.example.seebook.domain.profile.dto.request.ProfileWithReviewListRequestDTO;
import com.example.seebook.domain.profile.dto.response.ProfileWithReviewListResponseDTO;
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

    public void getProfileWithReviewListDTO(ProfileWithReviewListRequestDTO profileWithReviewListRequestDTO){
//        List<Tuple> results = queryFactory
//                .select(profile.imageUrl, user.nickname, levelInfo.level, levelInfo.levelCount, review.starRating.avg().as("avgStar"),
//                        review.book.bookId, book.imageLink, book.title , review.content)
//                .from(profile)
//                .leftJoin(levelInfo).on(levelInfo.userId.eq(profileWithReviewListRequestDTO.getUserId()))
//                .leftJoin(review).on(review.user.userId.eq(profileWithReviewListRequestDTO.getUserId()))
//                .leftJoin(book).on(book.bookId.eq(review.book.bookId))
//                .leftJoin(user).on(user.userId.eq(profileWithReviewListRequestDTO.getUserId()))
//                .where(profile.profileId.eq(profileWithReviewListRequestDTO.getUserId()))
//                .groupBy(review.book.bookId) //일단 데이터 가져왔는데 책의 평균 값을 어떻게 가져올지 모
//                .fetch();
//
//        String profileImage = null;
//        String nickname = null;
//        int levelValue = 0;
//        int levelCount = 0;
//        List<ProfileReviewDTO> reviewList = new ArrayList<>();
//
//        for (Tuple tuple : results) {
//            if (profileImage == null) {
//                profileImage = tuple.get(profile.profileImage);
//                nickname = tuple.get(profile.nickname);
//                levelValue = tuple.get(level.level);
//                levelCount = tuple.get(level.levelCount);
//            }
//            reviewList.add(ProfileWithReviewListResponseDTO.ReviewDTO.builder()
//                    .bookId(tuple.get(review.bookId))
//                    .cover(tuple.get(book.cover))
//                    .title(tuple.get(book.title))
//                    .avgStar(tuple.get(book.avgStar))
//                    .totalReviewCount(tuple.get(book.totalReviewCount))
//                    .content(tuple.get(review.content))
//                    .build());
//        }
//
//        return ProfileWithReviewListResponseDTO.builder()
//                .profileImage(profileImage)
//                .nickname(nickname)
//                .level(levelValue)
//                .levelCount(levelCount)
//                .totalReviewCount(reviewList.size())
//                .endPage(50) // Example endPage calculation
//                .reviewList(reviewList)
//                .build();
//    }
    }
}

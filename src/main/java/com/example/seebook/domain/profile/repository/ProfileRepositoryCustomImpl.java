package com.example.seebook.domain.profile.repository;

import com.example.seebook.domain.profile.dto.request.ProfileWithReviewListRequestDTO;
import com.example.seebook.domain.profile.dto.response.JoinResponseDTO;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.example.seebook.domain.level.domain.QLevelInfo.levelInfo;
import static com.example.seebook.domain.profile.domain.QProfile.profile;
import static com.example.seebook.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class ProfileRepositoryCustomImpl implements ProfileRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public void getProfileWithReviewListDTO(ProfileWithReviewListRequestDTO profileWithReviewListRequestDTO){
//        List<Tuple> results = queryFactory
//                .select(profile.imageUrl, user.nickname, levelInfo.level, levelInfo.levelCount,
//                        review.book.bookId, book.imageLink, book.title,
//                        review.starRating.avg().as("avgStar"), review.reviewId.count().as("totalReviewCount"))
//                .from(profile)
//                .leftJoin(levelInfo).on(levelInfo.userId.eq(profile.userId))
//                .leftJoin(user).on(user.userId.eq(profile.userId))//해당 사용자의 정보
//                .leftJoin(review).on(review.user.userId.eq(profile.userId))//해당 사용자가 작성한 모든 리뷰 리스트
//                .leftJoin(book).on(book.bookId.eq(review.book.bookId))// 그 리뷰 리스트에 맞는 책 정보를 가져옴
//                //해당 책의 모든 리뷰들을 가져와서 평균을 구해야 하는데 모름 따로 구성해야 하는가 서브쿼리 이용?
//                .where(profile.userId.eq(profileWithReviewListRequestDTO.getUserId()))
//                .groupBy(review.book.bookId)
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

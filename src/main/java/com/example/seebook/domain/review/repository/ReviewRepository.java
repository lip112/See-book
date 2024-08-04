package com.example.seebook.domain.review.repository;

import com.example.seebook.domain.review.domain.Review;
import com.example.seebook.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    void deleteByReviewIdAndUser(Long reviewId, User user);

    List<Review> findTop30ByOrderByReviewIdDesc();
    @Query("SELECT r " +
            "FROM Review r " +
            "WHERE r.reviewId IN (SELECT MAX(r2.reviewId) FROM Review r2 GROUP BY r2.book)" +
            "order by r.reviewId desc ")
    List<Review> findTop3DistinctReviews(Pageable pageable);

    void deleteByUser(User user);
}

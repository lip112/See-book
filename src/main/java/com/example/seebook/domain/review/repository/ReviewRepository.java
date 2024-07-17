package com.example.seebook.domain.review.repository;

import com.example.seebook.domain.review.domain.Review;
import com.example.seebook.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    void deleteByReviewIdAndUser(Long reviewId, User user);

    List<Review> findTop30ByOrderByReviewIdDesc();
    List<Review> findTop10ByOrderByReviewIdDesc();

    void deleteByUser(User user);
}

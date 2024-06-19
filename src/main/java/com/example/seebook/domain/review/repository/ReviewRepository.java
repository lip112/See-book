package com.example.seebook.domain.review.repository;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.review.domain.Review;
import com.example.seebook.domain.user.domain.User;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    void deleteByReviewIdAndUser(Long reviewId, User user);
}

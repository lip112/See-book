package com.example.seebook.domain;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.book.repository.BookRepository;
import com.example.seebook.domain.review.domain.Review;
import com.example.seebook.domain.review.repository.ReviewRepository;
import com.example.seebook.domain.role.domain.RoleCode;
import com.example.seebook.domain.role.domain.RoleInfo;
import com.example.seebook.domain.role.repository.RoleRepository;
import com.example.seebook.domain.user.domain.Gender;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.user.repository.UserRepository;
import com.example.seebook.domain.wishlist.domain.Wishlist;
import com.example.seebook.domain.wishlist.repository.WishlistRepository;
import com.example.seebook.global.exception.UserException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.BootstrapRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final WishlistRepository wishlistRepository;
    private final BookRepository bookRepository;
    private final PasswordEncoder passwordEncoder;
    private final TaskExecutor taskExecutor;
    Faker faker = new Faker(new java.util.Locale("ko"));

    @Override
    public void run(ApplicationArguments args) throws Exception {

        roleRepository.saveAll(Arrays.asList(
                new RoleInfo(RoleCode.ADMIN),
                new RoleInfo(RoleCode.USER)
        ));

//        List<Book> books = bookRepository.findAll();
//
//        // 비동기 작업 개수
//        int taskCount = 60000;
//
//        CountDownLatch latch = new CountDownLatch(taskCount);  // 작업 완료 대기용
//
//        // 시작 시간 기록
//        LocalDateTime start = LocalDateTime.now();
//
//        // 100개의 비동기 작업 제출
//        for (int i = 40001; i <= taskCount; i++) {
//            int finalI = i;
//            taskExecutor.execute(() -> {
//                try {
//                    // User 객체 생성 및 데이터 저장 작업
//                    User user = User.builder()
//                            .email("test" + finalI + "@test.com")
//                            .password(passwordEncoder.encode("StringSd$" + finalI))
//                            .nickname(faker.name().firstName() + faker.name().lastName() + faker.name().lastName())
//                            .name(faker.name().firstName() + faker.name().lastName())
//                            .gender(finalI % 2 == 0 ? Gender.FEMALE : Gender.MALE)
//                            .birthday(faker.date().birthday().toString().substring(0, 10))
//                            .phoneNumber(faker.phoneNumber().phoneNumber())
//                            .role(new RoleInfo(RoleCode.USER))
//                            .build();
//                    userRepository.save(user);
//                    System.out.println(Thread.currentThread().getName());
//                    review(user, books);
//                    wishList(user, books);
//                }
//                finally {
//                    latch.countDown(); // 작업이 끝날 때마다 카운트다운
//                }
//            });
//        }
//
//        // 비동기 작업들이 끝날 때까지 대기
//        latch.await();  // 모든 작업이 끝날 때까지 대기
//
//        // 종료 시간 기록
//        LocalDateTime end = LocalDateTime.now();
//
//        // 걸린 시간 출력
//        System.out.println("비동기 작업 완료 시간: " + (end.getSecond() - start.getSecond()) + "초");
    }

    @Transactional
    void review(User user, List<Book> books){
        List<Review> reviews = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String content = faker.lorem().paragraph();
            content = content.length() > 50 ? content.substring(0, 50) : content;

            Review review = Review.builder()
                    .user(user)
                    .book(books.get(faker.random().nextInt(0, books.size() - 1)))
                    .nickname(user.getNickname())
                    .content(content)
                    .starRating(faker.random().nextDouble(0.1, 5))
                    .build();
            reviews.add(review);
        }
        reviewRepository.saveAll(reviews); // Batch Insert 실행
    }

    @Transactional
    void wishList(User user, List<Book> books){
        List<Wishlist> wishlists = new ArrayList<>();

        for (int i =0; i < 20; i++){
            Wishlist wishlist = Wishlist.builder()
                    .user(user)
                    .book(books.get(faker.random().nextInt(0, 49)))
                    .build();
            wishlists.add(wishlist);
        }
        wishlistRepository.saveAll(wishlists);
    }
}
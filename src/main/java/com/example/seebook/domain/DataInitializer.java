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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final WishlistRepository wishlistRepository;
    private final BookRepository bookRepository;
    private final PasswordEncoder passwordEncoder;
    Faker faker = new Faker(new java.util.Locale("ko"));
    @Autowired
    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder,
                           WishlistRepository wishlistRepository, ReviewRepository reviewRepository, BookRepository bookRepository){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
        this.wishlistRepository = wishlistRepository;
        this.bookRepository = bookRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        roleRepository.saveAll(Arrays.asList(
                new RoleInfo(RoleCode.ADMIN),
                new RoleInfo(RoleCode.USER)
        ));

        List<Book> books = bookRepository.findAll();

        for(int i = 1; i <= 100; i++){
            System.out.println();
            User user = User.builder()
                    .email("test" + i + "@test.com")
                    .password(passwordEncoder.encode("StringSd$" + i))
                    .nickname(faker.name().firstName() + faker.name().lastName() + faker.name().lastName())
                    .name(faker.name().firstName() + faker.name().lastName())
                    .gender(i%2 == 0 ? Gender.FEMALE : Gender.MALE)
                    .birthday(faker.date().birthday().toString().substring(0, 10))
                    .phoneNumber(faker.phoneNumber().phoneNumber())
                    .role(new RoleInfo(RoleCode.USER))
                    .build();
            userRepository.save(user);
            review(user, books);
            wishList(user, books);

        }

    }

    void review(User user, List<Book> books){
        for (int i =0; i < 50; i++){
            String content = faker.lorem().paragraph();
            content = content.length() > 50 ? content.substring(0, 50) : content;

            Review review = Review.builder()
                    .user(user)
                    .book(books.get(faker.random().nextInt(0, 49)))
                    .nickname(user.getNickname())
                    .content(content)
                    .starRating(faker.random().nextDouble(0.1, 5))
                    .build();
            reviewRepository.save(review);
        }
    }

    void wishList(User user, List<Book> books){
        for (int i =0; i < 20; i++){
            Wishlist wishlist = Wishlist.builder()
                    .user(user)
                    .book(books.get(faker.random().nextInt(0, 49)))
                    .build();
            wishlistRepository.save(wishlist);
        }
    }
}

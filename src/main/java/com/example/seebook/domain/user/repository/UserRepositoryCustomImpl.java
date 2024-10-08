package com.example.seebook.domain.user.repository;

import com.example.seebook.domain.role.domain.RoleCode;
import com.example.seebook.domain.role.domain.RoleInfo;
import com.example.seebook.domain.suspend.dto.SuspendDTO;
import com.example.seebook.domain.user.domain.Gender;
import com.example.seebook.domain.user.dto.UserDTO;
import com.example.seebook.domain.user.dto.response.AdminUserDetailResponseDTO;
import com.example.seebook.domain.user.dto.response.AdminUserListResponseDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.seebook.domain.profile.domain.QProfile.profile;
import static com.example.seebook.domain.review.domain.QReview.review;
import static com.example.seebook.domain.suspend.domain.QSuspend.suspend;
import static com.example.seebook.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public AdminUserListResponseDTO findAdminUserList(int offset, int limit, String queryType, String query) {
        List<UserDTO> userList = jpaQueryFactory
                .select(user.userId, user.email, user.name, user.nickname,
                        user.gender, user.role.Description, user.createdDate)
                .from(user)
                .where(eqQueryType(queryType, query))
                .offset(offset)
                .limit(limit)
                .fetch()
                .stream()
                .map(tuple -> UserDTO.builder()
                        .userId(tuple.get(user.userId))
                        .email(tuple.get(user.email))
                        .name(tuple.get(user.name))
                        .nickname(tuple.get(user.nickname))
                        .gender(tuple.get(user.gender))
                        .role(RoleCode.fromDescription(tuple.get(user.role.Description)))
                        .createdDate(tuple.get(user.createdDate))
                        .build())
                .collect(Collectors.toList());
        Long count = jpaQueryFactory
                .select(user.count())
                .from(user)
                .where(eqQueryType(queryType, query))
                .fetchOne();
        return AdminUserListResponseDTO.builder()
                .totalUserCount(count)
                .endPage((count / 5) + 1)
                .user(userList)
                .build();
    }

    private BooleanExpression eqQueryType(String queryType, String query) {
        if (!queryType.equals("all")) {
            if (queryType.equals("email")) {
                return user.email.contains(query);
            } else if (queryType.equals("name")) {
                return user.name.contains(query);
            } else if (queryType.equals("nickname")) {
                return user.nickname.contains(query);
            } else if (queryType.equals("gender")) {
                return user.gender.eq(Gender.fromString(query));
            } else if (queryType.equals("role")) {
                return user.role.eq(new RoleInfo(RoleCode.fromDescription(query)));
            }
        }
        return null;
    }

    @Override
    public AdminUserDetailResponseDTO findAdminUserDetail(Long userId) {
        Tuple tuple = jpaQueryFactory
                .select(profile.imageUrl, user.email, user.nickname, user.name,
                        user.gender, user.role.Description, user.password,
                        suspend.startDate, suspend.endDate, suspend.reason)
                .from(user)
                .leftJoin(profile).on(profile.userId.eq(user.userId))
                .leftJoin(suspend).on(suspend.userId.eq(user.userId))
                .where(user.userId.eq(userId))
                .fetchOne();

        return AdminUserDetailResponseDTO.builder()
                .userId(userId)
                .profileImage(tuple.get(profile.imageUrl))
                .email(tuple.get(user.email))
                .nickname(tuple.get(user.nickname))
                .name(tuple.get(user.name))
                .gender(tuple.get(user.gender).name())
                .role(tuple.get(user.role.Description))
                .password(tuple.get(user.password))
                .suspend(SuspendDTO.builder()
                        .startDate(tuple.get(suspend.startDate))
                        .endDate(tuple.get(suspend.endDate))
                        .reason(tuple.get(suspend.reason))
                        .build())
                .build();
    }


}

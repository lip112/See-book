package com.example.seebook.domain.user.repository;

import com.example.seebook.domain.role.domain.RoleCode;
import com.example.seebook.domain.suspend.dto.SuspendDTO;
import com.example.seebook.domain.user.domain.Gender;
import com.example.seebook.domain.user.dto.UserDTO;
import com.example.seebook.domain.user.dto.response.AdminUserDetailResponseDTO;
import com.example.seebook.domain.user.dto.response.AdminUserListResponseDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.seebook.domain.profile.domain.QProfile.profile;
import static com.example.seebook.domain.suspend.domain.QSuspend.suspend;
import static com.example.seebook.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public AdminUserListResponseDTO findAdminUserList(int offset, int limit, String queryType, String query) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(user.userId.isNotNull());

        if (!query.equals("all")) {
            if (queryType.equals("email")) {
                builder.and(user.email.contains(query));
            } else if (queryType.equals("name")) {
                builder.and(user.name.contains(query));
            } else if (queryType.equals("nickname")) {
                builder.and(user.nickname.contains(query));
            } else if (queryType.equals("gender")) {
                builder.and(user.gender.eq(Gender.valueOf(query)));
            } else if (queryType.equals("role")) {
                builder.and(user.role.Code.eq(RoleCode.valueOf(query)));
            } else if (queryType.equals("createDate")) {
                builder.and(user.createdDate.eq(LocalDateTime.parse(query, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
            }
        }

        List<UserDTO> userList = jpaQueryFactory
                .select(user.userId, user.email, user.name, user.nickname,
                        user.gender, user.role.Description, user.createdDate)
                .from(user)
                .where(builder)
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
                        .role(RoleCode.valueOf(tuple.get(user.role.Description)))
                        .createdDate(tuple.get(user.createdDate))
                        .build())
                .collect(Collectors.toList());
        Long count = jpaQueryFactory
                .select(user.count())
                .from(user)
                .fetchOne();
        return AdminUserListResponseDTO.builder()
                .totalUserCount(count)
                .endPage((count / 10) + 1)
                .user(userList)
                .build();
    }

    @Override
    public AdminUserDetailResponseDTO findAdminUserDetail(Long userId) {
        Tuple tuple = jpaQueryFactory
                .select(profile.imageUrl, user.email, user.nickname, user.name,
                        user.gender, user.role.Description, user.password,
                        suspend.startDate, suspend.endDate, suspend.reason)
                .from(user)
                .innerJoin(profile).on(profile.userId.eq(user.userId))
                .innerJoin(suspend).on(suspend.userId.eq(user.userId))
                .where(user.userId.eq(userId))
                .fetchOne();

        return AdminUserDetailResponseDTO.builder()
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

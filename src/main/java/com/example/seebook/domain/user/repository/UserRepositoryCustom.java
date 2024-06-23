package com.example.seebook.domain.user.repository;

import com.example.seebook.domain.user.dto.response.AdminUserDetailResponseDTO;
import com.example.seebook.domain.user.dto.response.AdminUserListResponseDTO;

public interface UserRepositoryCustom {

    AdminUserListResponseDTO findAdminUserList(int offset, int limit, String queryType, String query);

    AdminUserDetailResponseDTO findAdminUserDetail(Long userId);
}

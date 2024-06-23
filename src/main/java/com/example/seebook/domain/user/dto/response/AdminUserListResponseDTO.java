package com.example.seebook.domain.user.dto.response;

import com.example.seebook.domain.user.dto.UserDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class AdminUserListResponseDTO {
    private Long totalUserCount;
    private Long endPage;
    private List<UserDTO> user;

    @Builder
    public AdminUserListResponseDTO(Long totalUserCount, Long endPage, List<UserDTO> user) {
        this.totalUserCount = totalUserCount;
        this.endPage = endPage;
        this.user = user;
    }
}

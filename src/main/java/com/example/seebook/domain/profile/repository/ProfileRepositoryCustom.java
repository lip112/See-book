package com.example.seebook.domain.profile.repository;

import com.example.seebook.domain.profile.dto.request.ProfileWithReviewListRequestDTO;

public interface ProfileRepositoryCustom {
    void getProfileWithReviewListDTO(ProfileWithReviewListRequestDTO profileWithReviewListRequestDTO);

    void join();
}

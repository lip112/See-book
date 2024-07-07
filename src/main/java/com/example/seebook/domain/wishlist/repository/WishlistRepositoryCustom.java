package com.example.seebook.domain.wishlist.repository;

import com.example.seebook.domain.wishlist.dto.response.GetWishlistResponseDTO;

public interface WishlistRepositoryCustom {
    GetWishlistResponseDTO getWIshlist(Long userId, int offset, int limit);
}

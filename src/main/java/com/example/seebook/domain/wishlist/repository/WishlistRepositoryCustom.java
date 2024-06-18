package com.example.seebook.domain.wishlist.repository;

import com.example.seebook.domain.wishlist.dto.request.GetWishlistRequestDTO;
import com.example.seebook.domain.wishlist.dto.response.GetWishlistResponseDTO;

public interface WishlistRepositoryCustom {
    GetWishlistResponseDTO getWIshlist(GetWishlistRequestDTO getWishlistRequestDTO, int offset, int limit);
}

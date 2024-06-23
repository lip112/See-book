package com.example.seebook.domain.user.service;

import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.user.dto.requset.AdminUserDetailRequestDTO;
import com.example.seebook.domain.user.dto.requset.LoginRequestDTO;
import com.example.seebook.domain.user.dto.response.AdminUserDetailResponseDTO;
import com.example.seebook.domain.user.dto.response.AdminUserListResponseDTO;
import com.example.seebook.domain.user.repository.UserRepository;
import com.example.seebook.global.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(UserException.NotFoundEmailException::new);
        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new UserException.LoginFailedException();
        }
        //jwt 토큰 발급 해야함
    }

    public void logout() {
    }

    public AdminUserListResponseDTO getUserList(int page, String queryType, String query) {
        return userRepository.findAdminUserList((page-1)*10, page*10-1, queryType, query);
    }

    public AdminUserDetailResponseDTO getUserDetail(AdminUserDetailRequestDTO adminUserDetailRequestDTO) {
        return userRepository.findAdminUserDetail(adminUserDetailRequestDTO.getUserId());
    }
}

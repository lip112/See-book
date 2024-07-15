package com.example.seebook.domain.user.service;

import com.example.seebook.domain.profile.domain.Profile;
import com.example.seebook.domain.profile.repository.ProfileRepository;
import com.example.seebook.domain.role.domain.RoleCode;
import com.example.seebook.domain.role.domain.RoleInfo;
import com.example.seebook.domain.suspend.domain.Suspend;
import com.example.seebook.domain.suspend.repository.SuspendRepository;
import com.example.seebook.domain.user.domain.Gender;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.user.dto.requset.AdminUserDeleteRequestDTO;
import com.example.seebook.domain.user.dto.requset.AdminUserModifyRequestDTO;
import com.example.seebook.domain.user.dto.requset.LoginRequestDTO;
import com.example.seebook.domain.user.dto.response.AdminUserDetailResponseDTO;
import com.example.seebook.domain.user.dto.response.AdminUserListResponseDTO;
import com.example.seebook.domain.user.repository.UserRepository;
import com.example.seebook.global.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.seebook.global.config.UserDefaultConfig.DEFAULT_PASSWORD;

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
        return userRepository.findAdminUserList((page-1)*5, 5, queryType, query);
    }

    public AdminUserDetailResponseDTO getUserDetail(Long userId) {
        return userRepository.findAdminUserDetail(userId);
    }

    public void modifyUser(AdminUserModifyRequestDTO adminUserModifyRequestDTO) {
        User user = userRepository.findById(adminUserModifyRequestDTO.getUserId())
                .orElseThrow(UserException.NotFoundUserException::new);

        if (adminUserModifyRequestDTO.isResetPassword()) {
            user.changePassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        }
        user.changeNickname(adminUserModifyRequestDTO.getNickname());
        user.changeGender(Gender.fromString(adminUserModifyRequestDTO.getGender()));
        user.changeRole(new RoleInfo(RoleCode.fromDescription(adminUserModifyRequestDTO.getRole())));

        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(AdminUserDeleteRequestDTO adminUserDeleteRequestDTO){
        userRepository.deleteAllByIdInBatch(adminUserDeleteRequestDTO.getUserId());
    }
}

package com.example.seebook.domain.user.service;

import com.example.seebook.domain.profile.domain.Profile;
import com.example.seebook.domain.profile.repository.ProfileRepository;
import com.example.seebook.domain.role.domain.RoleCode;
import com.example.seebook.domain.role.domain.RoleInfo;
import com.example.seebook.domain.suspend.domain.Suspend;
import com.example.seebook.domain.suspend.repository.SuspendRepository;
import com.example.seebook.domain.user.domain.Gender;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.user.dto.requset.AdminUserDetailRequestDTO;
import com.example.seebook.domain.user.dto.requset.AdminUserModifyRequestDTO;
import com.example.seebook.domain.user.dto.requset.LoginRequestDTO;
import com.example.seebook.domain.user.dto.response.AdminUserDetailResponseDTO;
import com.example.seebook.domain.user.dto.response.AdminUserListResponseDTO;
import com.example.seebook.domain.user.repository.UserRepository;
import com.example.seebook.global.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.amazonaws.services.ec2.model.PrincipalType.Role;

@Service
@RequiredArgsConstructor
public class AdminUserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final SuspendRepository suspendRepository;
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

    public void modifyUser(AdminUserModifyRequestDTO adminUserModifyRequestDTO) {
        User user = userRepository.findById(adminUserModifyRequestDTO.getUserId())
                .orElseThrow(UserException.NotFoundUserException::new);
        user.changeNickname(adminUserModifyRequestDTO.getNickname());
        user.changePassword(passwordEncoder.encode(adminUserModifyRequestDTO.getPassword()));
        user.changeGender(Gender.valueOf(adminUserModifyRequestDTO.getGender()));
        user.changeRole(new RoleInfo(RoleCode.valueOf(adminUserModifyRequestDTO.getRole())));
        userRepository.save(user);

        Profile profile = profileRepository.findByUserId(adminUserModifyRequestDTO.getUserId())
                        .orElseThrow(UserException.NotFoundUserException::new);
        profile.resetDefaultImage();
        profileRepository.save(profile);

        Suspend suspend = suspendRepository.findByUserId(adminUserModifyRequestDTO.getUserId())
                .orElseThrow(UserException.NotFoundUserException::new);
        suspend.changeDate(adminUserModifyRequestDTO.getSuspend().getStartDate(), adminUserModifyRequestDTO.getSuspend().getEndDate());
        suspendRepository.save(suspend);
    }
}

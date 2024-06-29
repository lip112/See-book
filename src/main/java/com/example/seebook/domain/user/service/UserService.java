package com.example.seebook.domain.user.service;

import com.example.seebook.domain.role.domain.RoleCode;
import com.example.seebook.domain.role.domain.RoleInfo;
import com.example.seebook.domain.suspend.domain.Suspend;
import com.example.seebook.domain.suspend.repository.SuspendRepository;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.user.dto.oauth2.KaKaoSignUpRequestDTO;
import com.example.seebook.domain.user.dto.oauth2.LoginResponse;
import com.example.seebook.domain.user.dto.oauth2.Oauth2DTO;
import com.example.seebook.domain.user.dto.oauth2.Oauth2SignUpResponseDTO;
import com.example.seebook.domain.user.dto.requset.ChangePasswordRequestDTO;
import com.example.seebook.domain.user.dto.requset.LoginRequestDTO;
import com.example.seebook.domain.user.dto.requset.SignUpRequestDTO;
import com.example.seebook.domain.user.dto.response.LoginResponseDTO;
import com.example.seebook.domain.suspend.dto.SuspendDTO;
import com.example.seebook.domain.user.repository.UserRepository;
import com.example.seebook.global.exception.UserException;
import com.example.seebook.global.exception.UserException.NotFoundEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final SuspendRepository suspendRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean checkRegistration(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).isPresent();
    }
    public User loginToKakao(Oauth2DTO oauth2DTO) {
        return userRepository.findByPhoneNumber(oauth2DTO.getPhoneNumber()).get();
    }

    public User loginToEmail(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(NotFoundEmailException::new);

        if(passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new UserException.LoginFailedException();
        }
    }

    public Long signUp(SignUpRequestDTO signUpRequestDTO) {
        Optional<User> byPhoneNumber = userRepository.findByPhoneNumber(signUpRequestDTO.getPhoneNumber());

        if (byPhoneNumber.isPresent()) {
            throw new UserException.DuplicatedPhoneNumberException();
        }

        User user = User.builder()
                .email(signUpRequestDTO.getEmail())
                .password(passwordEncoder.encode(signUpRequestDTO.getPassword()))
                .nickname(signUpRequestDTO.getNickname())
                .name(signUpRequestDTO.getNickname())
                .gender(signUpRequestDTO.getGender())
                .birthday(signUpRequestDTO.getBirthday())
                .phoneNumber(signUpRequestDTO.getPhoneNumber())
                .role(new RoleInfo(RoleCode.USER))
                .build();
        userRepository.save(user);
        return user.getUserId();
    }

    public Long signUpKaKao(KaKaoSignUpRequestDTO signUpRequestDTO) {
        Optional<User> byPhoneNumber = userRepository.findByPhoneNumber(signUpRequestDTO.getPhoneNumber());

        if (byPhoneNumber.isPresent()) {
            throw new UserException.DuplicatedPhoneNumberException();
        }

        User user = User.builder()
                .kakaoId(signUpRequestDTO.getKakaoId())
                .email(signUpRequestDTO.getEmail())
                .password(passwordEncoder.encode(signUpRequestDTO.getPassword()))
                .nickname(signUpRequestDTO.getNickname())
                .name(signUpRequestDTO.getNickname())
                .gender(signUpRequestDTO.getGender())
                .birthday(signUpRequestDTO.getBirthday())
                .phoneNumber(signUpRequestDTO.getPhoneNumber())
                .role(new RoleInfo(RoleCode.USER))
                .build();
        userRepository.save(user);
        return user.getUserId();
    }


    public String getEmail(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(UserException.NotFoundEmailException::new)
                .getEmail();
    }
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserException.NotFoundUserException::new);
    }

    public void changePassword(ChangePasswordRequestDTO changePasswordRequestDTO) {
        User user = userRepository.findByEmail(changePasswordRequestDTO.getEmail())
                .orElseThrow(UserException.NotFoundEmailException::new);
        user.changePassword(changePasswordRequestDTO.getPassword());
        userRepository.save(user);
    }

    public boolean validationPassword(ChangePasswordRequestDTO changePasswordRequestDTO) {
        User user = userRepository.findByEmail(changePasswordRequestDTO.getEmail())
                .orElseThrow(UserException.NotFoundEmailException::new);
        return passwordEncoder.matches(user.getPassword(), changePasswordRequestDTO.getPassword());
    }



    public boolean validationNickname(String nickname) {
        Optional<User> byNickname = userRepository.findByNickname(nickname);
        return byNickname.isEmpty();
    }
    public boolean validationEmail(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        return byEmail.isEmpty();
    }

    public void changeNickname(Long userId, String nickname) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserException.NotFoundUserException::new);
        user.changeNickname(nickname);
        userRepository.save(user);
    }

    public void deleteAccount(Long userId) {
        userRepository.deleteById(userId);
    }
}

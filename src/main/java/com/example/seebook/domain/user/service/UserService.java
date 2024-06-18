package com.example.seebook.domain.user.service;

import com.example.seebook.domain.role.domain.RoleCode;
import com.example.seebook.domain.role.domain.RoleInfo;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.user.dto.oauth2.LoginResponse;
import com.example.seebook.domain.user.dto.oauth2.Oauth2DTO;
import com.example.seebook.domain.user.dto.oauth2.Oauth2LoginResponseDTO;
import com.example.seebook.domain.user.dto.oauth2.Oauth2SignUpRequestDTO;
import com.example.seebook.domain.user.dto.requset.ChangePasswordRequestDTO;
import com.example.seebook.domain.user.dto.requset.LoginRequestDTO;
import com.example.seebook.domain.user.dto.requset.SignUpRequestDTO;
import com.example.seebook.domain.user.dto.response.LoginResponseDTO;
import com.example.seebook.domain.user.dto.response.Suspend;
import com.example.seebook.domain.user.repository.UserRepository;
import com.example.seebook.global.exception.UserException;
import com.example.seebook.global.exception.UserException.NotFoundEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse processOAuth2Login(Oauth2DTO oauth2DTO) {
        Optional<User> byPhoneNumber = userRepository.findByPhoneNumber(oauth2DTO.getPhoneNumber());
        if (byPhoneNumber.isPresent()) {
            //이미 가입된 경우 바로 정보 반환
            return Oauth2LoginResponseDTO.form(byPhoneNumber.get(), new Suspend(false, LocalDateTime.now(), LocalDateTime.now(), "임시"));
        } else {
            //최초로그인 -> 카카오 정보를 반환 -> 토탈 회원가입 할때 api로 가입시킴
            return Oauth2SignUpRequestDTO.form(oauth2DTO);
        }
    }
    public Long signUp(SignUpRequestDTO signUpRequestDTO) {
        userRepository.findByPhoneNumber(signUpRequestDTO.getPhoneNumber())
                .orElseThrow(UserException.DuplicatedPhoneNumberException::new);

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

    public LoginResponseDTO loginToEmail(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(NotFoundEmailException::new);
        if(passwordEncoder.matches(user.getPassword(), loginRequestDTO.getPassword()))
            return LoginResponseDTO.form(user, new Suspend(false, LocalDateTime.now(), LocalDateTime.now(), "임시"));
        else
            throw new UserException.LoginFailedException();
    }

    public boolean validationNickname(String nickname) {
        Optional<User> byNickname = userRepository.findByNickname(nickname);
        return byNickname.isEmpty();
    }

    public void changeNickname(Long userId, String nickname) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserException.NotFoundUserException::new);
        user.changeNickname(nickname);
        userRepository.save(user);
    }
}

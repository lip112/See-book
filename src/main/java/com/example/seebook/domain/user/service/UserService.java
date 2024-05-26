package com.example.seebook.domain.user.service;

import com.example.seebook.domain.roletype.entity.RoleCode;
import com.example.seebook.domain.roletype.entity.RoleType;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.user.dto.requset.ChangePasswordRequestDTO;
import com.example.seebook.domain.user.dto.requset.LoginRequestDTO;
import com.example.seebook.domain.user.dto.requset.sms.SignUpRequestDTO;
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

    public void signUp(SignUpRequestDTO signUpRequestDTO) {
//        userRepository.findByPhoneNumber(signUpRequestDTO.getPhoneNumber())
//                .orElseThrow(UserException.DuplicatedPhoneNumberException::new);

        User user = User.builder()
                .email(signUpRequestDTO.getEmail())
                .password(passwordEncoder.encode(signUpRequestDTO.getPassword()))
                .nickname(signUpRequestDTO.getNickname())
                .name(signUpRequestDTO.getNickname())
                .gender(signUpRequestDTO.getGender())
                .birthday(signUpRequestDTO.getBirthday())
                .phoneNumber(signUpRequestDTO.getPhoneNumber())
                .role(new RoleType(RoleCode.USER))
                .build();
        userRepository.save(user);
    }

    public String findEmail(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(UserException.NotFoundEmailException::new)
                .getEmail();
    }

    public void changePassword(ChangePasswordRequestDTO changePasswordRequestDTO) {
        User user = userRepository.findByEmail(changePasswordRequestDTO.getEmail())
                .orElseThrow(UserException.NotFoundEmailException::new);
        user.changePassword(changePasswordRequestDTO.getPassword());
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(NotFoundEmailException::new);
        if(passwordEncoder.matches(user.getPassword(), loginRequestDTO.getPassword()))
            return LoginResponseDTO.form(user, new Suspend(false, LocalDateTime.now(), LocalDateTime.now(), "임시"));
        else
            throw new UserException.LoginFailedException();
    }
}

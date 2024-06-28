package com.example.seebook.global.jwt;

import com.example.seebook.domain.user.repository.UserRepository;
import com.example.seebook.global.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override // userName은 이메일
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return CustomUserDetails.from(userRepository.findByEmail(username)
                .orElseThrow(UserException.NotFoundUserException::new));
    }
}

package com.example.seebook.global.jwt;

import com.example.seebook.global.exception.JwtException;
import com.example.seebook.global.exception.UserException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter { // OncePerRequestFilter -> 한 번 실행 보장

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7);
                log.info(token);

                if (jwtProvider.validateToken(token)) {
                    Claims authentication = jwtProvider.getAuthentication(token);
                    String username = authentication.get("email", String.class);

                    CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

                    if (userDetails != null) {
                        //UserDetsils, Password, Role -> 접근권한 인증 Token 생성, 하지만 비밀번호는 인증으로 사용을 안 하기에 null
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            }
        } catch (JwtException | UserException e) {
            log.error("Exception shows in filter = {}", e.toString());
            request.setAttribute("exception", e);
        }
        filterChain.doFilter(request, response); // 정상적인 경우 필터 체인 계속 진행
    }
}

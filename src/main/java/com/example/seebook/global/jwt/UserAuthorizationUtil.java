package com.example.seebook.global.jwt;


import com.example.seebook.global.exception.JwtException;
import com.example.seebook.global.exception.UserException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class UserAuthorizationUtil {
    private UserAuthorizationUtil() {
        throw new AssertionError(); //만약 인스턴스화를 시도하면 오류가 발생한다
    }
    public static Long getLoginUserId() {
        try{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return userDetails.getUserId();
        } catch (Exception e) {
            throw new JwtException.ExpiredJwtException();
        }
    }
    public static String getLoginUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return userDetails.getUsername();
    }
    public static Collection<? extends GrantedAuthority> getLoginUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return userDetails.getAuthorities();
    }
}

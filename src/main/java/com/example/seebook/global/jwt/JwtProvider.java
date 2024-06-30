package com.example.seebook.global.jwt;

import com.example.seebook.domain.refreshToken.domain.RefreshToken;
import com.example.seebook.domain.refreshToken.repository.RefreshTokenRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtProvider {

    @Value("${jwt.secret}") // application.properties에 있는 secret key
    private String secretKey;

    //key는 실제로 JWT를 서명하고 검증할 때 사용하는 Key 객체입니다. 이 객체는 secretKey를 기반으로 초기화됩니다.
    private Key key;

    private final long accessTokenValidTime = (60 * 1000) * 60 * 24; // 60분
    private final long refreshTokenValidTime = (60 * 1000) * 60 * 24 * 7; // 7일

    private final RefreshTokenRepository refreshTokenRepository; // refresh token을 저장하는 로직 추가해야함

    @PostConstruct //init() 메서드는 이 클래스의 인스턴스가 생성된 후 자동으로 호출되게 하는 어노테이션
    protected void init() {
        // key를 base64로 인코딩
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        key = Keys.hmacShaKeyFor(encodedKey.getBytes());
    }

    public String createAccessToken(CustomUserDetails userDetails) {
        return this.createToken(userDetails, accessTokenValidTime);
    }

    public String createRefreshToken(CustomUserDetails userDetails) {
        return this.createToken(userDetails, refreshTokenValidTime);
    }

    private String createToken(CustomUserDetails userDetails, Long accessTokenValidTime) {
        Claims claims = Jwts.claims();
        claims.put("userId", userDetails.getUser().getUserId());
        claims.put("email", userDetails.getUsername());
        claims.put("role", userDetails.getAuthorities());

        Date now = new Date();
        Date expiration = new Date(now.getTime() + accessTokenValidTime); // 만료 시간

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now) // 발행 시간
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS512) // (비밀키, 해싱 알고리즘)
                .compact();
    }


    public Claims getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    public Long getUserId(String token) {
        Claims claims = getAuthentication(token);
        return claims.get("userId", Long.class);
    }

    @Transactional(readOnly = true)
    public String getRefreshToken(Long userId) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("refresh token이 존재하지 않습니다."));
        return refreshToken.getRefreshToken();
    }
}


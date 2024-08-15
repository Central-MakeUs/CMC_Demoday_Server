package com.example.cmc_backend.jwt;


import static com.example.cmc_backend.common.CmcConstants.*;
import static com.example.cmc_backend.common.exception.errorCode.UserAuthErrorCode.*;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.cmc_backend.common.exception.UnauthorizedException;
import com.example.cmc_backend.user.domain.User;
import com.example.cmc_backend.user.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.refresh}")
    private String jwtRefresh;

    @Value("${jwt.access-token-seconds}")
    private long accessTokenSeconds;

    @Value("${jwt.refresh-token-seconds}")
    private long refreshTokenSeconds;


    private final UserRepository userRepository;


    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    private Key getRefreshKey() {
        return Keys.hmacShaKeyFor(jwtRefresh.getBytes(StandardCharsets.UTF_8));
    }


    public String createToken(Long userId) {
        Date now =new Date();

        final Key encodedKey = getSecretKey();

        return Jwts.builder()
                .setHeaderParam("type","jwt")
                .claim("userId",userId)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis()+accessTokenSeconds))
                .signWith(encodedKey)
                .compact();
    }

    public String createRefreshToken(Long userId){

        Date now=new Date();
        final Key encodedKey = getRefreshKey();

		return Jwts.builder()
                .setHeaderParam("type","refresh")
                .claim("userId",userId)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis()+refreshTokenSeconds))
                .signWith(encodedKey)
                .compact();
    }

    public Authentication getAuthentication(String token)  {
        Jws<Claims> claims;

        claims = Jwts.parser()
                .setSigningKey(getSecretKey())
                .parseClaimsJws(token);


        Long userId=claims.getBody().get("userId",Long.class);

        Optional<User> users=userRepository.findById(userId);

        return new UsernamePasswordAuthenticationToken(users.get(),"",users.get().getAuthorities());
    }


    public boolean validateToken(ServletRequest servletRequest, String token) {
        try {
            Jws<Claims> claims;
            claims = Jwts.parser()
                    .setSigningKey(getSecretKey())
                    .parseClaimsJws(token);

            Long userId = claims.getBody().get("userId",Long.class);

            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            servletRequest.setAttribute("exception","MalformedJwtException");
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            servletRequest.setAttribute("exception","ExpiredJwtException");
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            servletRequest.setAttribute("exception","UnsupportedJwtException");
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            servletRequest.setAttribute("exception","IllegalArgumentException");
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    public String getJwt(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader(AUTHORIZATION_HEADER);
    }

    public String getRefreshToken(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader(REFRESH_TOKEN_HEADER);
    }

    public Date getExpiredTime(String token){
        //받은 토큰의 유효 시간을 받아오기
        return Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody().getExpiration();
    }

    public Long getUserIdByRefreshToken(String refreshToken) {
        Jws<Claims> claims;
        try {
            claims = Jwts.parser()
                .setSigningKey(getRefreshKey())
                .parseClaimsJws(refreshToken);

        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new UnauthorizedException(INVALID_TOKEN_EXCEPTION);
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException(EXPIRED_JWT_EXCEPTION);
        } catch (UnsupportedJwtException e) {
            throw new UnauthorizedException(UNSUPPORTED_JWT_TOKEN);
        } catch (IllegalArgumentException e) {
            throw new UnauthorizedException(INVALID_TOKEN);
        }
        return claims.getBody().get("userId",Long.class);

    }

    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }

}

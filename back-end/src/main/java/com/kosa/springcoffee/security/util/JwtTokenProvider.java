package com.kosa.springcoffee.security.util;

import com.kosa.springcoffee.entity.MemberRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.Set;

@Log4j2
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("spring.jwt.secret")
    private String secretKey;

    // 1시간 유효
    private long tokenValidTime = 1000L * 60 * 60;

    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // JWT 토큰 생성
    public String generateToken(String userEmail, Set<MemberRole> roles) throws UnsupportedEncodingException {
        Claims claims = Jwts.claims().setSubject(userEmail);
        claims.put("roles", roles);
        Date curDate = new Date();

        String jwtToken = Jwts.builder()
                .setClaims(claims) // 데이터
                .setIssuedAt(curDate) // 토큰 발행일자
                .setExpiration(new Date(curDate.getTime() + tokenValidTime)) // 유효 시간 설정
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8")) // 암호화 알고리즘 , secret값 세팅팅
               .compact();

        return jwtToken;
    }

    // JWT 토큰으로 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserInfo(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

   // JWT 토큰에서 회원 구별 정보 추출
   public String getUserInfo(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
   }

    // Request Header에서 토큰 파싱 : "X-AUTH-TOKEN: jwt 토큰"
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    // JWT 토큰의 유효성, 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

}

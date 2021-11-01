package com.kosa.springcoffee.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kosa.springcoffee.dto.member.MemberResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class JwtDecoder {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public MemberResponseDTO decodeJwt(String token) {
        DecodedJWT decodedJWT = isValidToken(token)
                .orElseThrow(() -> new NoSuchElementException("유효한 토큰이 아닙니다."));

        String username = decodedJWT
                .getClaim("USERNAME")
                .asString();

        String role = decodedJWT
                .getClaim("USER_ROLE")
                .asString();

        return new MemberResponseDTO(username, role);
    }

    private Optional<DecodedJWT> isValidToken(String token) {
        DecodedJWT jwt = null;

        try {
            Algorithm algorithm = Algorithm.HMAC256("SCJWT");
            JWTVerifier verifier = JWT
                    .require(algorithm)
                    .build();

            jwt = verifier.verify(token);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return Optional.ofNullable(jwt);
    }
}
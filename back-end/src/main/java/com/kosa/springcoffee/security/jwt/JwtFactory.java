package com.kosa.springcoffee.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtFactory {

    private static final Logger log = LoggerFactory.getLogger(JwtFactory.class);
    private static final Date exp = new Date(System.currentTimeMillis() + 1000*60*60*24);
    // 1.
    public String generateToken(UserDetails userDetails) {
        String token = null;
        try {
            Set<String> roles = userDetails.getAuthorities().stream()
                    .map(r -> r.getAuthority()).collect(Collectors.toSet());
            String role = roles.iterator().next();

            token = JWT.create()
                    .withIssuer("SpringCoffee")
                    .withClaim("USERNAME", userDetails.getUsername())
                    .withClaim("USER_ROLE", role)
                    .withClaim("exp", exp)
                    .sign(generateAlgorithm());
            log.info(String.valueOf(exp));
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return token;
    }

    // 2.
    private Algorithm generateAlgorithm() throws UnsupportedEncodingException {
        String signingKey = "SCJWT";
        return Algorithm.HMAC256(signingKey);
    }
}

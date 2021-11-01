package com.kosa.springcoffee.security.provider;

import com.kosa.springcoffee.dto.member.MemberResponseDTO;
import com.kosa.springcoffee.security.jwt.JwtDecoder;
import com.kosa.springcoffee.security.token.JwtPreProcessingToken;
import com.kosa.springcoffee.security.token.PostAuthorizationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationProvider implements AuthenticationProvider {

    private final JwtDecoder jwtDecoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String     token      = (String) authentication.getPrincipal();
        MemberResponseDTO memberResponseDTO = jwtDecoder.decodeJwt(token);

        return PostAuthorizationToken.getTokenFormUserDetails(memberResponseDTO);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtPreProcessingToken.class.isAssignableFrom(authentication);
    }
}
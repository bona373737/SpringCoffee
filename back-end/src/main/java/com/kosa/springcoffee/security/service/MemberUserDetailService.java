package com.kosa.springcoffee.security.service;

import com.kosa.springcoffee.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberUserDetailService implements UserDetailsService{

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        log.info("user mail : " + userEmail);

        UserDetails userDetails = memberRepository.findByEmail(userEmail);

        return userDetails;
    }
}
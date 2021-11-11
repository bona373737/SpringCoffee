package com.kosa.springcoffee.security.service;

import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional
    public void joinMember(Member member){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    public Boolean checkEmail(String email) {
        if(memberRepository.checkEmail(email)>0){
            return false;
        } else return true;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        log.info("user mail : " + userEmail);

        UserDetails userDetails = memberRepository.findByEmail(userEmail);

        return userDetails;
    }
}
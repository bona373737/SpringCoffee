package com.kosa.login.security.service;

import com.kosa.login.entity.Member;
import com.kosa.login.repository.MemberRepository;
import com.kosa.login.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional
    public void joinMember(Member member){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("MemberUserDetailsService loadUserByUsername " + username);
        //return null;
        Optional<Member> result = memberRepository.findByEmail(username , false);

        Member member = result.get();

        log.info("----------------");
        log.info(member);


        AuthMemberDTO authMember = new AuthMemberDTO(
                member.getEmail(),
                member.getPassword(),
                member.isFromSocial(),
                member.getRoleSet().stream().
                        map(role -> new SimpleGrantedAuthority(
                                 "ROLE_" + role.name()))
                        .collect(Collectors.toSet())
        );
        authMember.setName(member.getName());
        authMember.setFromSocial(member.isFromSocial());

        return authMember;
    }
}

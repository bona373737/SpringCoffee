package com.kosa.springcoffee.service.member;

import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> member = this.memberRepository.findByUsername(username);

        /**
         * Username 값이 DATA DB 에 존재하지 않는 경우
         * UsernameNotFoundException 에러 메소드를 사용합니다.
         * */
        if (member.isPresent()) {
            return User.builder()
                    .username(member.get().getUsername())
                    .password(member.get().getPassword())
                    .roles(member.get().getRole().getKey())
                    .build();
        } else {
            throw new UsernameNotFoundException(username + "정보를 찾을 수 없습니다.");
        }
    }

    @Override
    public Member saveOrUpdateMember(Member member) {
        member.encodePassword(this.passwordEncoder);

        return this.memberRepository.save(member);
    }
}

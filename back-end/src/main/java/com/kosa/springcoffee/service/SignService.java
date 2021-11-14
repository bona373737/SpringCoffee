package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.LoginRequestDTO;
import com.kosa.springcoffee.dto.LoginResponseDTO;
import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.repository.MemberRepository;
import com.kosa.springcoffee.security.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class SignService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void joinMember(Member member){
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    public Boolean checkEmail(String email) {
        if(memberRepository.checkEmail(email)>0){
            return false;
        } else return true;
    }


    public LoginResponseDTO login(LoginRequestDTO dto) throws Exception {
        String email = dto.getEmail();
        String password = dto.getPassword();

        Member member = memberRepository.getById(email);

        if(!passwordEncoder.matches(password, member.getPassword())){
            throw new Exception();
        }

        String token = jwtTokenProvider.generateToken(email, member.getRoles());

        LoginResponseDTO responseDTO = LoginResponseDTO.builder().email(email).token(token).build();

        return responseDTO;
    }
}

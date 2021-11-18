package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.MemberRequestDTO;
import com.kosa.springcoffee.dto.LoginResponseDTO;
import com.kosa.springcoffee.dto.ModifyMemberReqeustDTO;
import com.kosa.springcoffee.dto.MyPageResponseDTO;
import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.repository.MemberRepository;
import com.kosa.springcoffee.security.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void joinMember(Member member){
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    public Boolean checkEmail(String email) {
        return !memberRepository.existsByEmail(email);
    }


    public LoginResponseDTO login(MemberRequestDTO dto) throws Exception {
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

    public void modifyUserInfo(ModifyMemberReqeustDTO dto) {
        String email = dto.getEmail();
        Optional<Member> result = memberRepository.getByEmail(email, false);

        if (result.isPresent()){
            Member member = result.get();
            member.changePassword(passwordEncoder.encode(dto.getPassword()));
            member.changeName(dto.getName());
            member.changeAddress(dto.getAddress());
            memberRepository.save(member);
        }
    }

    public void modifyPassword(MemberRequestDTO dto) {
        String email = dto.getEmail();
        Optional<Member> result = memberRepository.getByEmail(email, false);

        if (result.isPresent()) {
            Member member = result.get();
            member.changePassword(passwordEncoder.encode(dto.getPassword()));
            memberRepository.save(member);
        }
    }

    public MyPageResponseDTO getUserInfo(String email) {
        Optional<Member> result = memberRepository.getByEmail(email, false);

        if (result.isPresent()){
            Member member = result.get();

            MyPageResponseDTO responseDTO = new MyPageResponseDTO().builder()
                    .email(member.getEmail())
                    .name(member.getName())
                    .address(member.getAddress()).build();

            return responseDTO;
        }
        return null;
    }

    public Boolean verifyUser(MemberRequestDTO dto) {
        Member member = memberRepository.getById(dto.getEmail());

        if(passwordEncoder.matches(dto.getPassword(), member.getPassword())){
            return true;
        }

        return false;
    }
}

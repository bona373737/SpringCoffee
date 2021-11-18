package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.*;
import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.entity.MemberRole;
import com.kosa.springcoffee.repository.MemberRepository;
import com.kosa.springcoffee.security.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    public void modifyUserInfo(ModifyMemberInfoReqeustDTO dto) {
        String email = dto.getEmail();
        Optional<Member> result = memberRepository.getByEmail(email, false);

        if (result.isPresent()){
            Member member = result.get();
            if (dto.getNewPassword().length()>0) {
                member.changePassword(passwordEncoder.encode(dto.getNewPassword()));
            }
            member.changeName(dto.getName());
            member.changeAddress(dto.getAddress());
            memberRepository.save(member);
        }
    }

    public void modifyUserInfoAdmin(ModifyMemberInfoAdminDTO dto) {
        String email = dto.getEmail();
        Optional<Member> result = memberRepository.getByEmail(email, false);

        if (result.isPresent()) {
            Member member = result.get();
            member.changeName(dto.getName());
            member.changeAddress(dto.getAddress());
            memberRepository.save(member);
        }
    }

    public void modifyPassword(ModifyPasswordRequestDTO dto) {
        String email = dto.getEmail();
        Optional<Member> result = memberRepository.getByEmail(email, false);

        if (result.isPresent()) {
            Member member = result.get();
            member.changePassword(passwordEncoder.encode(dto.getNewPassword()));
            memberRepository.save(member);
        }
    }

    public void grantUserToAdmin(String email) {
        Optional<Member> result = memberRepository.getByEmail(email, false);

        if (result.isPresent()) {
            Member member = result.get();
            member.removeMemberRole();
            member.addMemberRole(MemberRole.ROLE_ADMIN);
            memberRepository.save(member);
        }
    }

    public void removeAdmin(String email) {
        Optional<Member> result = memberRepository.getByEmail(email, false);

        if (result.isPresent()) {
            Member member = result.get();
            member.removeMemberRole();
            member.addMemberRole(MemberRole.ROLE_USER);
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

    public PageResultDTO<MyPageResponseDTO, Member> getUserList(PageRequestDTO requestDTO) {

        List<Member> memberList = memberRepository.findAll();

        List<MyPageResponseDTO> dtoList = new ArrayList<>();

        memberList.forEach(member -> {
            if (member.getRoles().contains(MemberRole.ROLE_USER)){
                dtoList.add(getUserInfo(member.getEmail()));
            }
        });

        Pageable pageable = requestDTO.getPageable();

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), dtoList.size());
        Page<MyPageResponseDTO> result = new PageImpl<>(dtoList.subList(start, end), pageable, dtoList.size());

        return new PageResultDTO<>(result);
    }

    public PageResultDTO<MyPageResponseDTO, Member> getAdminList(PageRequestDTO requestDTO) {

        List<Member> memberList = memberRepository.findAll();

        List<MyPageResponseDTO> dtoList = new ArrayList<>();

        memberList.forEach(member -> {
            if (member.getRoles().contains(MemberRole.ROLE_ADMIN)){
                dtoList.add(getUserInfo(member.getEmail()));
            }
        });

        Pageable pageable = requestDTO.getPageable();

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), dtoList.size());
        Page<MyPageResponseDTO> result = new PageImpl<>(dtoList.subList(start, end), pageable, dtoList.size());

        return new PageResultDTO<>(result);
    }

    public Boolean verifyUser(MemberRequestDTO dto) {
        Member member = memberRepository.getById(dto.getEmail());

        if(passwordEncoder.matches(dto.getPassword(), member.getPassword())){
            return true;
        }

        return false;
    }

    public void deleteUser(String email) {
        Optional<Member> result = memberRepository.getByEmail(email, false);

        if (result.isPresent()) {
            Member member = result.get();
            memberRepository.delete(member);
        }
    }
}

package com.kosa.springcoffee.controller;

import com.kosa.springcoffee.dto.SignUpDTO;
import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.security.service.MemberUserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import static com.kosa.springcoffee.entity.MemberRole.ROLE_ADMIN;
import static com.kosa.springcoffee.entity.MemberRole.ROLE_USER;
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/v5")
public class SignController {

    private final MemberUserDetailService memberUserDetailService;

    @PostMapping("/email-check")
    public Boolean checkEmail(@RequestBody String email){
        return memberUserDetailService.checkEmail(email);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignUpDTO dto){
        Member member = Member.builder()
                        .email(dto.getEmail())
                        .password(dto.getPassword())
                        .name(dto.getName())
                        .fromSocial(dto.isFromSocial()).build();

        if(dto.getIsAdmin() == 1){
            member.addMemberRole(ROLE_ADMIN);
        }
        member.addMemberRole(ROLE_USER);
        memberUserDetailService.joinMember(member);
        return "redirect:/login";
    }
}
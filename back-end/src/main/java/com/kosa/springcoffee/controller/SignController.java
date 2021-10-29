package com.kosa.springcoffee.controller;

import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.security.service.MemberUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.kosa.springcoffee.entity.MemberRole.ADMIN;
import static com.kosa.springcoffee.entity.MemberRole.USER;
@Controller
@Log4j2
@RequiredArgsConstructor
public class SignController {
    private final MemberUserDetailsService memberUserDetailService;

    @GetMapping("/signUp")
    public void signUpForm(){
        log.info("회원가입--------------------------");
    }

    @PostMapping("/signUp")
    public String signup(Member member, Long isAdmin){

        if(isAdmin == 1){
            member.addMemberRole(ADMIN);
        }
        member.addMemberRole(USER);
        memberUserDetailService.joinMember(member);
        return "redirect:/login";
    }
}

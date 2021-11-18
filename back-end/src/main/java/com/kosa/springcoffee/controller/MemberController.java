package com.kosa.springcoffee.controller;

import com.kosa.springcoffee.dto.*;
import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.repository.MemberRepository;
import com.kosa.springcoffee.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.kosa.springcoffee.entity.MemberRole.ROLE_ADMIN;
import static com.kosa.springcoffee.entity.MemberRole.ROLE_USER;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/v5")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/email-check")
    public Boolean checkEmail(@RequestParam String email){
        return memberService.checkEmail(email);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignUpDTO dto){
        Member member = Member.builder()
                        .email(dto.getEmail())
                        .password(dto.getPassword())
                        .name(dto.getName())
                        .address(dto.getAddress())
                        .fromSocial(dto.isFromSocial()).build();

        if(dto.getIsAdmin() == 1){
            member.addMemberRole(ROLE_ADMIN);
        } else {
            member.addMemberRole(ROLE_USER);
        }
        memberService.joinMember(member);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody MemberRequestDTO loginDTO) throws Exception {
        LoginResponseDTO dto = memberService.login(loginDTO);

        Optional<Member> result = memberRepository.getByEmail(loginDTO.getEmail(), false);
        Member member = result.get();

        if (member == null){
            return new ResponseEntity<String>("아이디가 존재하지 않습니다.", HttpStatus.FORBIDDEN);
        }
        if (!memberService.verifyUser(loginDTO)){
            return new ResponseEntity<String>("비밀번호가 틀립니다.", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<LoginResponseDTO>(dto, HttpStatus.OK);
    }

    @PostMapping("/update-userinfo")
    public ResponseEntity modifyUserInfo(@RequestBody ModifyMemberInfoReqeustDTO dto) {

        MemberRequestDTO verifyPassword = MemberRequestDTO.builder()
                .email(dto.getEmail()).password(dto.getCurPassword()).build();

        if (memberService.verifyUser(verifyPassword)) {
            memberService.modifyUserInfo(dto);
            return new ResponseEntity<String>("회원 정보가 수정되었습니다.", HttpStatus.OK);
        }

        return new ResponseEntity<String>("비밀번호가 일치하지 않습니다.", HttpStatus.OK);
    }

    @PostMapping("/admin/update-userinfo")
    public ResponseEntity modifyUserInfoAdmin(@RequestBody ModifyMemberInfoAdminDTO dto) {

        memberService.modifyUserInfoAdmin(dto);

        return new ResponseEntity("이메일 " + " 회원 정보가 수정되었습니다.", HttpStatus.OK);
    }

    @PostMapping("/update-password")
    public ResponseEntity modifyPassword(@RequestBody ModifyPasswordRequestDTO dto) {

        MemberRequestDTO verifyPassword = MemberRequestDTO.builder()
                .email(dto.getEmail()).password(dto.getCurPassword()).build();

        if (memberService.verifyUser(verifyPassword)) {
            memberService.modifyPassword(dto);
            return new ResponseEntity<String>("비밀번호가 수정되었습니다.", HttpStatus.OK);
        }
        return new ResponseEntity<String>("비밀번호가 일치하지 않습니다", HttpStatus.OK);
    }

    @PostMapping("/grant-role")
    public ResponseEntity grantUserToAdmin(@RequestParam String email) {

        memberService.grantUserToAdmin(email);

        return new ResponseEntity(email + " 관리자 권한 부여", HttpStatus.OK);
    }

    @PostMapping("/remove-role")
    public ResponseEntity removeAdmin(@RequestParam String email) {

        memberService.removeAdmin(email);

        return new ResponseEntity(email + " 관리자 권한 삭제", HttpStatus.OK);
    }

    @GetMapping("/mypage")
    public ResponseEntity getUserInfo(@RequestParam String email) {
        MyPageResponseDTO responseDTO = memberService.getUserInfo(email);

        return new ResponseEntity<MyPageResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/userlist")
    public PageResultDTO<MyPageResponseDTO, Member> getUserList(PageRequestDTO pageRequestDTO) {
        log.info("회원 리스트 조회");

        return memberService.getUserList(pageRequestDTO);
    }

    @GetMapping("/adminlist")
    public PageResultDTO<MyPageResponseDTO, Member> getAdminList(PageRequestDTO pageRequestDTO) {
        log.info("관리자 리스트 조회");

        return memberService.getAdminList(pageRequestDTO);
    }

    @PostMapping("verifyuser")
    public ResponseEntity verifyUser(@RequestBody MemberRequestDTO dto){
        Boolean result = memberService.verifyUser(dto);

        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestParam String email) {

        memberService.deleteUser(email);

        return new ResponseEntity(email + " 계정 삭제", HttpStatus.OK);
    }
}
package com.kosa.springcoffee.controller.v0;

import com.kosa.springcoffee.dto.member.MemberRequestDTO;
import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.service.member.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v0")
public class MemberController {

    private final MemberServiceImpl memberService;

    @PostMapping("/sign")
    public ResponseEntity<?> insertMember(
            @Valid @RequestBody MemberRequestDTO dto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Member memberDB = memberService.saveOrUpdateMember(dto.toEntity());

        return new ResponseEntity<>(memberDB, HttpStatus.CREATED);
    }

    @GetMapping("/sign")
    public ResponseEntity<?> viewMember() {
        return new ResponseEntity<>("스프링 카페에 오신 것을 환영합니다!", HttpStatus.OK);
    }
}

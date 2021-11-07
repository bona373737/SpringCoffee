package com.kosa.springcoffee.controller;

import com.kosa.springcoffee.dto.QnaReplyDTO;
import com.kosa.springcoffee.service.QnaReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v4")
@Log4j2
@RequiredArgsConstructor
public class QnaReplyController {

    private final QnaReplyService qnaReplyService;

    @PostMapping("/register")
    public ResponseEntity<Long> create(@RequestBody QnaReplyDTO dto) {
        log.info(dto.getQnaBoardNo() + " 답변 등록");
        System.out.println(dto);
        Long num = qnaReplyService.create(dto);
        return new ResponseEntity<>(num, HttpStatus.OK);
    }

}

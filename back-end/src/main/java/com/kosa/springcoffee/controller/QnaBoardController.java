package com.kosa.springcoffee.controller;

import com.kosa.springcoffee.dto.*;
import com.kosa.springcoffee.entity.QnaBoard;
import com.kosa.springcoffee.service.QnaBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v3")
@Log4j2
@RequiredArgsConstructor
public class QnaBoardController {

    private final QnaBoardService qnaBoardService;

    @PostMapping("/register")
    public ResponseEntity<Long> create(@RequestBody QnaBoardDTO dto) {
        Long num = qnaBoardService.create(dto);
        log.info("게시글 등록");
        return new ResponseEntity<>(num, HttpStatus.OK);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResultDTO<QnaBoardDTO, QnaBoard> readAll(PageRequestDTO pageRequestDTO) {
        log.info("Q&A 전체 조회");
        return qnaBoardService.readAll(pageRequestDTO);
    }

    @GetMapping(value = "/{qnaBoardNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QnaBoardDTO> read(@PathVariable("qnaBoardNo") Long qnaBoardNo){
        log.info(qnaBoardNo+" 상세 페이지");
        return new ResponseEntity<>(qnaBoardService.get(qnaBoardNo), HttpStatus.OK);
    }

    @GetMapping(value = "/list/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResultDTO<QnaBoardDTO, QnaBoard> getCategory(CategoryPageRequestDTO pageRequestDTO, @PathVariable String category) {
        log.info("Q&A " + category + "조회");
        return qnaBoardService.getCategory(pageRequestDTO);
    }

    @GetMapping(value = "/list/all/{isAnswered}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResultDTO<QnaBoardDTO, QnaBoard> getAnswered(AnsweredPageRequestDTO pageRequestDTO, @PathVariable Boolean isAnswered) {
        log.info("Q&A " + isAnswered + "조회");
        return qnaBoardService.getAnswered(pageRequestDTO);
    }

    @GetMapping(value = "/list/{category}/{isAnswered}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResultDTO<QnaBoardDTO, QnaBoard> getCategoryAndAnswered(QnaCategoryAnsweredPageRequestDTO pageRequestDTO, @PathVariable String category, @PathVariable Boolean isAnswered) {
        log.info("Q&A " + category + ", " + isAnswered + " 조회");
        System.out.println(pageRequestDTO);
        return qnaBoardService.getCategoryAndAnswered(pageRequestDTO);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<QnaBoardDTO>> getList(@RequestParam(value = "email") String email){
        log.info(email + " 검색");
        return new ResponseEntity<>(qnaBoardService.getAllWithWriter(email), HttpStatus.OK);
    }

    @GetMapping(value = "/search/keyword")
    public PageResultDTO<QnaBoardDTO, QnaBoard> searchKeyword(KeywordPageRequestDTO requestDTO) {
        log.info(requestDTO.getKeyword() + " 검색");
        return qnaBoardService.searchKeyword(requestDTO);
    }

    @GetMapping(value = "/search/email")
    public PageResultDTO<QnaBoardDTO, QnaBoard> searchEmail(EmailPageRequestDTO requestDTO) {
        log.info(requestDTO.getEmail() + " 검색");
        return qnaBoardService.searchEmail(requestDTO);
    }

    @PutMapping(value = "/{qnaBoardNo}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> modify(@RequestBody QnaBoardDTO qnaBoardDTO){
        log.info(qnaBoardDTO);
        qnaBoardService.modify(qnaBoardDTO);
        return new ResponseEntity<>("modified", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{qnaBoardNo}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@PathVariable("qnaBoardNo") Long qnaBoardNo){
        log.info(qnaBoardNo);
        qnaBoardService.remove(qnaBoardNo);
        return new ResponseEntity<>("removed", HttpStatus.OK);
    }
}

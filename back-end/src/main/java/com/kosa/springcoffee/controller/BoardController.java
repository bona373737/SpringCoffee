package com.kosa.springcoffee.controller;

import com.kosa.springcoffee.dto.BoardDTO;
import com.kosa.springcoffee.dto.CategoryPageRequestDTO;
import com.kosa.springcoffee.dto.PageRequestDTO;
import com.kosa.springcoffee.dto.PageResultDTO;
import com.kosa.springcoffee.entity.Board;
import com.kosa.springcoffee.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/register")
    public ResponseEntity<Long> create(@RequestBody BoardDTO dto) {
        Long num = boardService.create(dto);
        log.info("게시글 등록");
        return new ResponseEntity<>(num, HttpStatus.OK);
    }

    @GetMapping(value = "/{boardNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardDTO> read(@PathVariable("boardNo") Long boardNo){
        log.info(boardNo+" 상세 페이지");
        return new ResponseEntity<>(boardService.get(boardNo), HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BoardDTO>> getList(String email){
        log.info(email);
        return new ResponseEntity<>(boardService.getAllWithWriter(email), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{boardNo}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@PathVariable("boardNo") Long boardNo){
        log.info(boardNo);
        boardService.remove(boardNo);
        return new ResponseEntity<>("removed", HttpStatus.OK);
    }

    @PutMapping(value = "/{boardNo}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> modify(@RequestBody BoardDTO boardDTO){
        log.info(boardDTO);
        boardService.modify(boardDTO);
        return new ResponseEntity<>("modified", HttpStatus.OK);
    }

    @GetMapping("/list")
    public PageResultDTO<BoardDTO, Board> readAll(PageRequestDTO pageRequestDTO) {
        log.info("게시글 전체 조회");
        return boardService.readAll(pageRequestDTO);
    }

    @GetMapping("/list/{category}")
    public PageResultDTO<BoardDTO, Board> getCategory(CategoryPageRequestDTO pageRequestDTO, @PathVariable String category) {
        log.info("게시글 "+category+" 조회");
        return boardService.getCategory(pageRequestDTO);
    }

}

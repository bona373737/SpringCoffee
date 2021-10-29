package com.kosa.springcoffee.controller;

import com.kosa.springcoffee.dto.BoardDTO;
import com.kosa.springcoffee.dto.CategoryPageRequestDTO;
import com.kosa.springcoffee.dto.PageRequestDTO;
import com.kosa.springcoffee.dto.PageResultDTO;
import com.kosa.springcoffee.entity.Board;
import com.kosa.springcoffee.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/register")
    public String create(@RequestBody BoardDTO dto) {
        boardService.create(dto);
        log.info("게시글 등록");

        return "redirect:/board/list";
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

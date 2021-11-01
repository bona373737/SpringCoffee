package com.kosa.springcoffee.controller;

import com.kosa.springcoffee.dto.board.BoardDTO;
import com.kosa.springcoffee.dto.board.PageRequestDTO;
import com.kosa.springcoffee.dto.board.PageResultDTO;
import com.kosa.springcoffee.entity.Board;
import com.kosa.springcoffee.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public PageResultDTO<BoardDTO, Board> readAll(PageRequestDTO pageRequestDTO) {
        log.info("게시글 전체 조회");
        return boardService.readAll(pageRequestDTO);
    }

}

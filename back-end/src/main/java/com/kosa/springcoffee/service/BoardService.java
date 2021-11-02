package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.BoardDTO;
import com.kosa.springcoffee.dto.CategoryPageRequestDTO;
import com.kosa.springcoffee.dto.PageRequestDTO;
import com.kosa.springcoffee.dto.PageResultDTO;
import com.kosa.springcoffee.entity.Board;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public interface BoardService {

    void create(BoardDTO dto);
    PageResultDTO<BoardDTO, Board> readAll(PageRequestDTO requestDTO); // 전체 조회
    PageResultDTO<BoardDTO, Board> getCategory(CategoryPageRequestDTO requestDTO); // 카테고리 조회

    default Board dtoToEntity(BoardDTO dto) {
        Board entity = Board.builder()
                .boardNo(dto.getBoardNo())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .category(dto.getCategory())
                .build();
        return entity;
    }

    default BoardDTO entityToDto(Board entity) {
        BoardDTO dto = BoardDTO.builder()
                .boardNo(entity.getBoardNo())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .category(entity.getCategory())
                .modDate(entity.getModDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .regDate(entity.getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
        return dto;
    }
}

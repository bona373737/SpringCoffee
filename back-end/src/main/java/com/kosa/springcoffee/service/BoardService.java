package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.BoardDTO;
import com.kosa.springcoffee.dto.PageRequestDTO;
import com.kosa.springcoffee.dto.PageResultDTO;
import com.kosa.springcoffee.entity.Board;
import org.springframework.stereotype.Service;

@Service
public interface BoardService {

    PageResultDTO<BoardDTO, Board> readAll(PageRequestDTO requestDTO); // 전체 조회

    default Board dtoToEntity(BoardDTO dto) {
        Board entity = Board.builder()
                .boardNo(dto.getBoardNo())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    default BoardDTO entityToDto(Board entity) {
        BoardDTO dto = BoardDTO.builder()
                .boardNo(entity.getBoardNo())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .build();
        return dto;
    }
}

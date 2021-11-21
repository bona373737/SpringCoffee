package com.kosa.springcoffee.noticeboard;

import com.kosa.springcoffee.base.dto.*;
import com.kosa.springcoffee.member.Member;
import com.kosa.springcoffee.noticeboard.dto.BoardDTO;

import java.time.format.DateTimeFormatter;
import java.util.List;

public interface BoardService {

    Long create(BoardDTO dto);

    BoardDTO get(Long boardNo);

    void modify(BoardDTO boardDTO);

    void remove(Long boardNo);

    List<BoardDTO> getAllWithWriter(String writerEmail);

    PageResultDTO<BoardDTO, Board> readAll(PageRequestDTO requestDTO); // 전체 조회

    PageResultDTO<BoardDTO, Board> getCategory(CategoryPageRequestDTO requestDTO); // 카테고리 조회

    PageResultDTO<BoardDTO, Board> searchKeyword(KeywordPageRequestDTO requestDTO); // 키워드 검색
    
    default Board dtoToEntity(BoardDTO dto) {
        Board entity = Board.builder()
                .boardNo(dto.getBoardNo())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(Member.builder().email(dto.getWriter()).build())
                .category(dto.getCategory())
                .build();
        return entity;
    }

    default BoardDTO entityToDto(Board entity) {
        BoardDTO dto = BoardDTO.builder()
                .boardNo(entity.getBoardNo())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter().getEmail())
                .category(entity.getCategory())
                .modDate(entity.getModDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .regDate(entity.getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
        return dto;
    }

}

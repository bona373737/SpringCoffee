//package com.kosa.springcoffee.service.board;
//
//import com.kosa.springcoffee.dto.board.BoardDTO;
//import com.kosa.springcoffee.dto.board.PageRequestDTO;
//import com.kosa.springcoffee.dto.board.PageResultDTO;
//import com.kosa.springcoffee.entity.Board;
//import org.springframework.stereotype.Service;
//
//@Service
//public interface BoardService {
//
//    PageResultDTO<BoardDTO, Board> readAll(PageRequestDTO requestDTO); // 전체 조회
//
//    default Board dtoToEntity(BoardDTO dto) {
//        Board entity = Board.builder()
//                .noticeNo(dto.getNoticeNo())
//                .title(dto.getTitle())
//                .content(dto.getContent())
//                .writer(dto.getWriter())
//                .build();
//        return entity;
//    }
//
//    default BoardDTO entityToDto(Board entity) {
//        BoardDTO dto = BoardDTO.builder()
//                .noticeNo(entity.getNoticeNo())
//                .title(entity.getTitle())
//                .content(entity.getContent())
//                .writer(entity.getWriter())
//                .build();
//        return dto;
//    }
//}

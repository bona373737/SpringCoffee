//package com.kosa.springcoffee.service.board;
//
//import com.kosa.springcoffee.dto.board.BoardDTO;
//import com.kosa.springcoffee.dto.board.PageRequestDTO;
//import com.kosa.springcoffee.dto.board.PageResultDTO;
//import com.kosa.springcoffee.entity.Board;
//import com.kosa.springcoffee.repository.BoardRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.util.function.Function;
//
//@Service
//@Log4j2
//@RequiredArgsConstructor
//public class BoardServiceImpl implements BoardService {
//
//    private final BoardRepository boardRepository;
//
//    @Override
//    public PageResultDTO<BoardDTO, Board> readAll(PageRequestDTO requestDTO) {
//        Pageable pageable = requestDTO.getPageable(Sort.by("noticeNo").descending());
//
//        Page<Board> result = boardRepository.findAll(pageable);
//
//        Function<Board, BoardDTO> fn = (entity -> entityToDto(entity));
//
//        return new PageResultDTO<>(result, fn);
//    }
//
//}

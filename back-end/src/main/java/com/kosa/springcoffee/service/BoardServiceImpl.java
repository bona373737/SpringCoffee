package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.BoardDTO;
import com.kosa.springcoffee.dto.CategoryPageRequestDTO;
import com.kosa.springcoffee.dto.PageRequestDTO;
import com.kosa.springcoffee.dto.PageResultDTO;
import com.kosa.springcoffee.entity.Board;
import com.kosa.springcoffee.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public void create(BoardDTO dto) {
        Board entity = dtoToEntity(dto);
        boardRepository.save(entity);
    }

    @Override
    public PageResultDTO<BoardDTO, Board> readAll(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("boardNo").descending());

        Page<Board> result = boardRepository.findAll(pageable);

        Function<Board, BoardDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<BoardDTO, Board> getCategory(CategoryPageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("boardNo").descending());

        Page<Board> result = boardRepository.findByCategory(requestDTO.getCategory(), pageable);

        Function<Board, BoardDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }
}

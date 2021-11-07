package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.*;
import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.entity.QnaBoard;

import java.time.format.DateTimeFormatter;
import java.util.List;

public interface QnaBoardService {

    Long create(QnaBoardDTO dto); // 등록

    PageResultDTO<QnaBoardDTO, QnaBoard> readAll(PageRequestDTO requestDTO); // 전체 조회

    QnaBoardDTO get(Long qnaBoardNo); // 상세 조회

    PageResultDTO<QnaBoardDTO, QnaBoard> getCategoryAndAnswered(QnaCategoryAnsweredPageRequestDTO requestDTO);

    PageResultDTO<QnaBoardDTO, QnaBoard> getCategory(CategoryPageRequestDTO requestDTO); // 카테고리 조회

    PageResultDTO<QnaBoardDTO, QnaBoard> getAnswered(AnsweredPageRequestDTO requestDTO); // 답변여부 조회

    List<QnaBoardDTO> getAllWithWriter(String writerEmail); // 작성자 조회

    void modify(QnaBoardDTO qnaBoardDTO);

    void modifyIsAnswered(QnaBoardDTO qnaBoardDTO);

    void remove(Long qnaBoardNo);


    default QnaBoard dtoToEntity(QnaBoardDTO dto) {
        QnaBoard entity = QnaBoard.builder()
                .qnaBoardNo(dto.getQnaBoardNo())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(Member.builder().email(dto.getWriter()).build())
                .category(dto.getCategory())
                .build();
        return entity;
    }

    default QnaBoardDTO entityToDto(QnaBoard entity) {
        QnaBoardDTO dto = QnaBoardDTO.builder()
                .qnaBoardNo(entity.getQnaBoardNo())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter().getEmail())
                .category(entity.getCategory())
                .isAnswered(entity.getIsAnswered())
                .modDate(entity.getModDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .regDate(entity.getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
        return dto;
    }
}

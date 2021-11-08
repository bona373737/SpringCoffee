package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.*;
import com.kosa.springcoffee.entity.QnaBoard;
import com.kosa.springcoffee.repository.QnaBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class QnaBoardServiceImpl implements QnaBoardService{

    private final QnaBoardRepository qnaBoardRepository;

    @Override
    public Long create(QnaBoardDTO dto) {
        QnaBoard entity = dtoToEntity(dto);
        qnaBoardRepository.save(entity);

        return entity.getQnaBoardNo();
    }

    @Override
    public PageResultDTO<QnaBoardDTO, QnaBoard> readAll(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("qnaBoardNo").descending());

        Page<QnaBoard> result = qnaBoardRepository.findAll(pageable);

        Function<QnaBoard, QnaBoardDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public QnaBoardDTO get(Long qnaBoardNo) {
        Optional<QnaBoard> result = qnaBoardRepository.getWithWriter(qnaBoardNo);

        if(result.isPresent()){
            return entityToDto(result.get());
        }

        return null;
    }

    @Override
    public PageResultDTO<QnaBoardDTO, QnaBoard> getCategory(CategoryPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("qnaBoardNo").descending());

        Page<QnaBoard> result = qnaBoardRepository.findByCategory(requestDTO.getCategory(), pageable);

        Function<QnaBoard, QnaBoardDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<QnaBoardDTO, QnaBoard> getAnswered(AnsweredPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("qnaBoardNo").descending());

        Page<QnaBoard> result = qnaBoardRepository.findByIsAnswered(requestDTO.getIsAnswered(), pageable);

        Function<QnaBoard, QnaBoardDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<QnaBoardDTO, QnaBoard> getCategoryAndAnswered(QnaCategoryAnsweredPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("qnaBoardNo").descending());

        Page<QnaBoard> result = qnaBoardRepository.findByCategoryAndIsAnswered(requestDTO.getCategory(), requestDTO.getIsAnswered(), pageable);

        Function<QnaBoard, QnaBoardDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public List<QnaBoardDTO> getAllWithWriter(String writerEmail) {
        List<QnaBoard> qnaBoardList = qnaBoardRepository.getList(writerEmail);

        return qnaBoardList.stream().map(qnaBoard -> entityToDto(qnaBoard))
                .collect(Collectors.toList());
    }

    @Override
    public PageResultDTO<QnaBoardDTO, QnaBoard> searchKeyword(KeywordPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("qnaBoardNo").descending());

        Page<QnaBoard> result = qnaBoardRepository.findByTitleContaining(requestDTO.getKeyword(), pageable);

        Function<QnaBoard, QnaBoardDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<QnaBoardDTO, QnaBoard> searchEmail(EmailPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("qnaBoardNo").descending());

        Page<QnaBoard> result = qnaBoardRepository.findByWriterEmailContaining(requestDTO.getEmail(), pageable);

        Function<QnaBoard, QnaBoardDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public void modify(QnaBoardDTO qnaBoardDTO) {
        Long qnaBoardNo = qnaBoardDTO.getQnaBoardNo();
        Optional<QnaBoard> result = qnaBoardRepository.findById(qnaBoardNo);

        if(result.isPresent()){
            QnaBoard qnaBoard = result.get();
            qnaBoard.changeTitle(qnaBoardDTO.getTitle());
            qnaBoard.changeContent(qnaBoardDTO.getContent());
            qnaBoardRepository.save(qnaBoard);
        }
    }

    @Override
    public void modifyIsAnswered(Long qnaBoardNo, Boolean isAnswered) {
        Optional<QnaBoard> result = qnaBoardRepository.findById(qnaBoardNo);

        if(result.isPresent()){
            QnaBoard qnaBoard = result.get();
            qnaBoard.changeIsAnswered(isAnswered);
            qnaBoardRepository.save(qnaBoard);
        }
    }

    @Override
    public void remove(Long qnaBoardNo) {
        qnaBoardRepository.deleteById(qnaBoardNo);
    }
}

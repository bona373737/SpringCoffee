package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.QnaReplyDTO;
import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.entity.QnaBoard;
import com.kosa.springcoffee.entity.QnaReply;
import com.kosa.springcoffee.repository.QnaReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class QnaReplyServiceImpl implements QnaReplyService{

    private final QnaReplyRepository qnaReplyRepository;

    private final QnaBoardService qnaBoardService;

    @Override
    public Long create(QnaReplyDTO dto) {

        QnaReply entity = QnaReply.builder()
                        .qnaReplyNo(dto.getQnaReplyNo())
                        .content(dto.getContent())
                        .replyer(Member.builder().email(dto.getReplyer()).build())
                        .qnaBoard(QnaBoard.builder().qnaBoardNo(dto.getQnaBoardNo()).build()).build();

        qnaReplyRepository.save(entity);
        qnaBoardService.modifyIsAnswered(dto.getQnaBoardNo(), true);

        return entity.getQnaReplyNo();
    }

    @Override
    public QnaReplyDTO get(Long qnaReplyNo) {
        Optional<QnaReply> result = qnaReplyRepository.findById(qnaReplyNo);

        if (result.isPresent()) {
            QnaReply entity = result.get();

            QnaReplyDTO qnaReplyDTO = QnaReplyDTO.builder()
                    .qnaReplyNo(entity.getQnaReplyNo())
                    .content(entity.getContent())
                    .replyer(entity.getReplyer().getEmail())
                    .qnaBoardNo(entity.getQnaBoard().getQnaBoardNo()).build();
            return qnaReplyDTO;
        }

        return null;
    }

    @Override
    public void modify(QnaReplyDTO dto) {
        Long qnaReplyNo = dto.getQnaReplyNo();
        Optional<QnaReply> result = qnaReplyRepository.findById(qnaReplyNo);

        if (result.isPresent()){
            QnaReply qnaReply = result.get();
            qnaReply.changeContent(dto.getContent());
            qnaReplyRepository.save(qnaReply);
        }
    }

    @Override
    public void remove(Long qnaReplyNo) {
        Long qnaBoardNo = qnaReplyRepository.findById(qnaReplyNo).get().getQnaBoard().getQnaBoardNo();
        qnaReplyRepository.deleteById(qnaReplyNo);
        qnaBoardService.modifyIsAnswered(qnaBoardNo, false);
    }
}

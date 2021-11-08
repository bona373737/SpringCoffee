package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.QnaReplyDTO;


public interface QnaReplyService {

    Long create(QnaReplyDTO dto); // 생성

    QnaReplyDTO get(Long qnaBoardNo); // 상세 조회

    void modify(QnaReplyDTO dto); // 수정

    void remove(Long qnaReplyNo); // 삭제
}

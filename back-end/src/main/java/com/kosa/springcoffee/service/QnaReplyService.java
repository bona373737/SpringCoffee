package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.QnaReplyDTO;


public interface QnaReplyService {

    Long create(QnaReplyDTO dto);

    QnaReplyDTO get(Long qnaBoardNo); // 상세 조회

    void modify(QnaReplyDTO dto);
}

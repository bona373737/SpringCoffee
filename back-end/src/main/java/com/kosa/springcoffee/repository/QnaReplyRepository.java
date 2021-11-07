package com.kosa.springcoffee.repository;

import com.kosa.springcoffee.entity.QnaReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QnaReplyRepository extends JpaRepository<QnaReply, Long> {

    Optional<QnaReply> findByQnaBoard(Long qna_board_no);
}

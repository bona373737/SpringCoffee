package com.kosa.springcoffee.qnareply;

import com.kosa.springcoffee.qnaboard.QnaBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QnaReplyRepository extends JpaRepository<QnaReply, Long> {

    Optional<QnaReply> findByQnaBoard(QnaBoard qnaBoard);

}

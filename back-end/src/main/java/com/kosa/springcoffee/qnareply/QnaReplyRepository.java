package com.kosa.springcoffee.board.qnareply;

import com.kosa.springcoffee.board.qnaboard.QnaBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QnaReplyRepository extends JpaRepository<QnaReply, Long> {

    Optional<QnaReply> findByQnaBoard(QnaBoard qnaBoard);

}

package com.kosa.springcoffee.repository;

import com.kosa.springcoffee.entity.QnaBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QnaBoardRepository extends JpaRepository<QnaBoard, Long> {
    Page<QnaBoard> findByCategory(String category, Pageable pageable);

    Page<QnaBoard> findByIsAnswered(Boolean isAnswered, Pageable pageable);

    Page<QnaBoard> findByCategoryAndIsAnswered(String category, Boolean isAnswered, Pageable pageable);

    Page<QnaBoard> findByTitleContaining(String keyword, Pageable pageable);

    Page<QnaBoard> findByWriterEmailContaining(String email, Pageable pageable);

    @EntityGraph(attributePaths = "writer", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select b from sc_qna_board b where b.qnaBoardNo = :qnaBoardNo")
    Optional<QnaBoard> getWithWriter(Long qnaBoardNo);

    @EntityGraph(attributePaths = {"writer"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select b from sc_qna_board b where b.writer.email = :email")
    List<QnaBoard> getList(String email);

}

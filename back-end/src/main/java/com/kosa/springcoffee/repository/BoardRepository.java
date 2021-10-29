package com.kosa.springcoffee.repository;

import com.kosa.springcoffee.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findByCategory(String category, Pageable pageable);
}

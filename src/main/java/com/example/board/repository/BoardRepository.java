package com.example.board.repository;

import com.example.board.dto.BoardDto;
import com.example.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableJpaAuditing
public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findByWriterOrderByWriterDesc(String writer);
    List<Board> findAllByOrderByIdDesc();

}

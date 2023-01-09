package com.example.board.repository;

import com.example.board.entity.BadUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableJpaAuditing
public interface BadUserRepository extends JpaRepository<BadUser,Long> {
    List<BadUser> findAllByOrderByIdDesc();
}

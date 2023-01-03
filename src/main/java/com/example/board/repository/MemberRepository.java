package com.example.board.repository;

import com.example.board.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Repository

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByEmail(String email);



}

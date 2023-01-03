package com.example.board.service;

import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import com.example.board.entity.Member;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public void commentSave(Long boardId, Comment comment, Member member) {
        Board board = boardRepository.findById(boardId).orElseThrow(IllegalAccessError::new);
        comment.save(board,member);
        commentRepository.save(comment);
    }
}

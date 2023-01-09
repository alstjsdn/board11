package com.example.board.service;

import com.example.board.dto.CommentDto;
import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import com.example.board.entity.Member;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    public void saveComment(CommentDto commentDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginUserEmail = authentication.getName();
        Member member = memberRepository.findByEmail(loginUserEmail);
        Board board = boardRepository.findById(commentDto.getBoardId()).orElseThrow();

        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setBoard(board);
        comment.setMember(member);
        comment.setWriter(member.getName());
        commentRepository.save(comment);
    }

    public List<CommentDto> getComments(Long boardId) {
        List<CommentDto> commentDtoList = new ArrayList<>();
        List<Comment> comments = commentRepository.findAllByBoardIdOrderByIdDesc(boardId);

        for (Comment comment : comments) {
            CommentDto dto = CommentDto.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .writer(comment.getWriter())
                    .boardId(comment.getBoard().getId())
                    .memberId(comment.getMember().getId())
                    .createdDate(comment.getCreatedDate())
                    .build();
            commentDtoList.add(dto);
        }
        return commentDtoList;
    }
}


package com.example.board.dto;

import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import com.example.board.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

    private Long id;
    private String writer;
    private String content;
    private Member member;
    private Board board;
    private LocalDateTime createdDate;



    public Comment toEntity(Long id, String writer, String content, Member member, Board board, LocalDateTime createdDate) {
        Comment comment =Comment.builder()
                .id(id)
                .writer(writer)
                .content(content)
                .createdDate(createdDate)
                .member(member)
                .board(board)
                .build();
        return comment;
    }


}

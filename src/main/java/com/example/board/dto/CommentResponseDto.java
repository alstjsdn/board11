package com.example.board.dto;

import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import com.example.board.entity.Member;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class CommentResponseDto {

    private Long id;
    private String writer;
    private String content;

    private LocalDateTime createdDate;

    private String name;

    private Long boardId;


    public CommentResponseDto(Comment comment) {
        this.id= comment.getId();;
        this.writer= comment.getWriter();
        this.content= comment.getContent();
        this.createdDate=comment.getCreatedDate();
        this.boardId=comment.getBoard().getId();

    }


}

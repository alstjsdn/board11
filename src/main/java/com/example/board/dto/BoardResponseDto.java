package com.example.board.dto;

import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardResponseDto {

    private Long id;
    private String title;
    private String writer;
    private String content;
    private Long memberId;
    private List<CommentResponseDto> comments;

    public BoardResponseDto(Board board) {
        this.id=board.getId();
        this.title= board.getTitle();
        this.writer= board.getWriter();
        this.content=board.getContent();
        this.memberId=board.getMember().getId();
        //comments 필드의 List 타입을 DTO 클래스로해서 엔티티간 무한 참조를 방지
        this.comments=board.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());

    }
}

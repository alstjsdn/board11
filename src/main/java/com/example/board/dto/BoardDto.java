package com.example.board.dto;

import com.example.board.entity.Board;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board toEntity() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();
        Board board =Board.builder()
                .id(id)
                .writer(username)
                .title(title)
                .content(content)
                .build();

        return board;
    }

    @Builder
    public BoardDto(Long id,String writer,String content,String title,LocalDateTime createdDate,LocalDateTime modifiedDate){
        this.id=id;
        this.writer=writer;
        this.content=content;
        this.title=title;
        this.createdDate=createdDate;
        this.modifiedDate=modifiedDate;
    }



}

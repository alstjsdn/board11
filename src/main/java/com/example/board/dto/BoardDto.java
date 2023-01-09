package com.example.board.dto;

import com.example.board.entity.Board;
import com.example.board.entity.Member;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class BoardDto {

    private Long id;
    private String writer;
    private String title;
    private String content;

    private Member memberId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


}

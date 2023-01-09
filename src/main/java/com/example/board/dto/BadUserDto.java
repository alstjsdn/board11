package com.example.board.dto;

import com.example.board.entity.BadUser;
import com.example.board.entity.Member;
import com.example.board.entity.TimeEntity;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
public class BadUserDto {

    private Long id;

    private String user;

    private String content;


    private Member member;

    private LocalDateTime createdDate;


}

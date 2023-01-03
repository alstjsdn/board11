package com.example.board.entity;

import lombok.*;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String writer;

    @Column(nullable = false)
    private String content;

    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Comment (Long id,String writer,String content,Member member,Board board,LocalDateTime createdDate) {
        this.id=id;
        this.writer=writer;
        this.content=content;
        this.createdDate=createdDate;
        this.board=board;
        this.member=member;
    }

    public void save(Board board, Member member) {
        this.board=board;
        this.member=member;

    }







}

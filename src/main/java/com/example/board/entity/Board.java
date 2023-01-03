package com.example.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mysql.cj.protocol.ColumnDefinition;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Board extends TimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;



    @Column(length = 20,nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Lob
    @Column(columnDefinition = "text",nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OrderBy("id desc")
    @JsonIgnoreProperties({"board"})
    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Comment> comments;


    @Builder
    public Board(Long id,String writer,String title,String content,Member member){
        this.id=id;
        this.writer=writer;
        this.title=title;
        this.content=content;
        this.member=member;
    }

    public void update(String title,String content) {
        this.title=title;
        this.content=content;
    }






}

package com.example.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mysql.cj.protocol.ColumnDefinition;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @JsonIgnoreProperties({"board"})
    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Comment> comments;









}

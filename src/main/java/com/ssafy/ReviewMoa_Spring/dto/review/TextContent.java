package com.ssafy.ReviewMoa_Spring.dto.review;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TextContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long textId;

    @ManyToOne
    @JoinColumn(name="postId")
    private Board board;

    @Column
    private int orders;

    //mysql에서 수동으로 text 타입으로 변경해주기
    private String content;
}

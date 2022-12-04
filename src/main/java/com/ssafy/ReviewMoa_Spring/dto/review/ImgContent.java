package com.ssafy.ReviewMoa_Spring.dto.review;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ImgContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;

    @ManyToOne
    @JoinColumn(name="postId")
    private Board board;

    @Column
    private int orders;

    private String url;
}

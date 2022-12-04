package com.ssafy.ReviewMoa_Spring.dto.review;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="postId")
    private Long postId;

    private String writer;
    private int hit;
    private String genre;
    private String movieTitle;
    private String postTitle;
    private int heart;
    private String thumbnail;

    @CreatedDate
    private LocalDate registTime;


}

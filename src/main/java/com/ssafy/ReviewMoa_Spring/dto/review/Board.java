package com.ssafy.ReviewMoa_Spring.dto.review;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
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

    private String content;

/*
    @OneToMany( mappedBy = "board", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Content> contentList = new ArrayList<>();

 */


}

package com.ssafy.ReviewMoa_Spring.dto.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;

//@AllArgsConstructor
//@Getter
//@Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Builder
@Entity
@Getter
@Setter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //아이디
    String content; //태그내용

    //게시글 하나에 여러 태그가 포함될 수 있음
    @ManyToOne
    @JoinColumn(name="postId")
    @JsonIgnore
    private Board board;


}

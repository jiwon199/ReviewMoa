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
@Data
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentId;
    int orders;
    int type; //글이면 0, 이미지면 1
    String content; //mysql 측에서 text 타입으로 바꿔주기

    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="postId")
    @JsonIgnore
    private Board board;


}

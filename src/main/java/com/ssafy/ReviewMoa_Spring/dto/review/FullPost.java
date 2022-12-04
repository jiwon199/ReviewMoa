package com.ssafy.ReviewMoa_Spring.dto.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;


@Data
public class FullPost {

    Board board;
    List<TextContent> textList;
    List<ImgContent> imgList;
    List <Content> contentList;
}

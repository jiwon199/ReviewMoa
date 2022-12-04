package com.ssafy.ReviewMoa_Spring.dto.review;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Content {
    int order;
    int type;
    String content;
}

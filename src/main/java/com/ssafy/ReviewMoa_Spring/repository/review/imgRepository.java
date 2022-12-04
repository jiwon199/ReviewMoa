package com.ssafy.ReviewMoa_Spring.repository.review;


import com.ssafy.ReviewMoa_Spring.dto.review.ImgContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface imgRepository extends JpaRepository<ImgContent, Long> {

}

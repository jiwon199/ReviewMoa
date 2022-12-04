package com.ssafy.ReviewMoa_Spring.repository.review;



import com.ssafy.ReviewMoa_Spring.dto.review.TextContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface contentRepository extends JpaRepository<TextContent, Long> {

}

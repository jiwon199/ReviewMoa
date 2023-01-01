package com.ssafy.ReviewMoa_Spring.repository.review;


import com.ssafy.ReviewMoa_Spring.dto.review.Board;
import com.ssafy.ReviewMoa_Spring.dto.review.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface tagRepository extends JpaRepository<Tag, Long> {

    void deleteByBoard(Board board);
}

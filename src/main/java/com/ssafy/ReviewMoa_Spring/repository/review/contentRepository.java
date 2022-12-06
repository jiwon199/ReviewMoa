package com.ssafy.ReviewMoa_Spring.repository.review;


import com.ssafy.ReviewMoa_Spring.dto.review.Board;
import com.ssafy.ReviewMoa_Spring.dto.review.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface contentRepository extends JpaRepository<Content, Long> {

    void deleteByBoard(Board board);
}

package com.ssafy.ReviewMoa_Spring.repository.review;


import com.ssafy.ReviewMoa_Spring.dto.review.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface boardRepository extends JpaRepository<Board, Long> {
}

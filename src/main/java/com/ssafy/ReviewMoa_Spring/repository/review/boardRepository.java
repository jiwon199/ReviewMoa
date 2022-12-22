package com.ssafy.ReviewMoa_Spring.repository.review;


import com.ssafy.ReviewMoa_Spring.dto.review.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface boardRepository extends JpaRepository<Board, Long> {
    public Page<Board> findAllByGenre(Pageable pageable, String genre);
    public Page<Board> findByPostTitleContains(Pageable pageable, String postTitle);
}

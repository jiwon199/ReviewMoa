package com.ssafy.ReviewMoa_Spring.controller.review;


import com.ssafy.ReviewMoa_Spring.dto.review.*;
import com.ssafy.ReviewMoa_Spring.repository.review.boardRepository;
import com.ssafy.ReviewMoa_Spring.service.review.boardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class boardController {
    /* http://localhost:9999/moa/board/ */
    @Autowired
    private boardService service;

    //포스트 저장하는 함수
    @PostMapping("/write")
    public void create(@RequestBody Board board) {
        service.createPost(board);
    }

    //포스트 목록 나열- 일단 findAll로 구현하고 페이징 등등은 나중에
    @GetMapping("/list")
    public List<Board> getList(){
        return service.getList();
    }

    //포스트 하나 반환
    @GetMapping("/list/{postId}")
    public Board getOne(@PathVariable Long postId) {
        Board board=service.getOne(postId);
        return board;
    }

    //포스트 수정
    @PostMapping("/update")
    public void update(@RequestBody Board board) {
        service.updatePostInfo(board);
        //service.updatePostContent(board);
    }

    //포스트 삭제
    @PostMapping("/delete/{postId}")
    public void deletePost(@PathVariable Long postId){
        service.deleteOne(postId);
    }

    //조회수 업데이트-todo
    //공감수 업데이트-todo



}

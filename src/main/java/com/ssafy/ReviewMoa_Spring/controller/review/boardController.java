package com.ssafy.ReviewMoa_Spring.controller.review;


import com.ssafy.ReviewMoa_Spring.dto.review.*;
import com.ssafy.ReviewMoa_Spring.dto.testUser;
import com.ssafy.ReviewMoa_Spring.repository.review.boardRepository;
import com.ssafy.ReviewMoa_Spring.repository.review.contentRepository;
import com.ssafy.ReviewMoa_Spring.repository.review.imgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class boardController {
    /* http://localhost:9999/moa/board/ */
    @Autowired
    private boardRepository boardRepo;
    @Autowired
    private contentRepository contentRepo;
    @Autowired
    private imgRepository imgRepo;

    //포스트 저장하는 함수
    @PostMapping("/write")
    public void create(@RequestBody FullPost post) {
        //대략적인 정보(board)를 먼저 저장하고
        setBoardInfo(post);

        //내용 목록의 f.k를 board로 설정
        for(TextContent content:post.getTextList()){
            content.setBoard(post.getBoard());
        }
        //이미지 목록의 f.k를 board로 설정
        for(ImgContent content:post.getImgList()){
            content.setBoard(post.getBoard());
        }
        //내용과 이미지 목록을 저장
        contentRepo.saveAll(post.getTextList());
        imgRepo.saveAll(post.getImgList());

    }

    public void setBoardInfo(FullPost post){
        Board board=post.getBoard();
        board.setRegistTime(LocalDate.now());

        if(post.getImgList().size()>=1){
            board.setThumbnail(post.getImgList().get(0).getUrl());
        }

        boardRepo.save(board);
    }

    //포스트 목록 나열- 일단 findAll로 구현하고 페이징 등등은 나중에
    @GetMapping("/list")
    public List<Board> getList(){
        return boardRepo.findAll();
    }

    //포스트 하나 반환
    @GetMapping("/list/{postId}")
    public FullPost getOne(@PathVariable Long postId) {
        FullPost post=new FullPost();
        //board 추가하고
        Board board=boardRepo.getReferenceById(postId);
        post.setBoard(board);

        //이미지와 글들을 순서에 맞게 추가한 목록
        List <Content> contents=new ArrayList<>();
        //content에 차례차례 넣는 로직 추가하기
        Collections.sort(contents, (a, b) -> b.getOrder() - a.getOrder());
        post.setContentList(contents);

        return post;
    }

   

    //포스트 삭제

    //포스트 수정



}

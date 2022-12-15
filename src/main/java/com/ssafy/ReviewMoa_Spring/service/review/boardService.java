package com.ssafy.ReviewMoa_Spring.service.review;
import com.ssafy.ReviewMoa_Spring.dto.review.Board;
import com.ssafy.ReviewMoa_Spring.dto.review.Content;
import com.ssafy.ReviewMoa_Spring.repository.review.boardRepository;
import com.ssafy.ReviewMoa_Spring.repository.review.contentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class boardService {

    @Autowired
    private boardRepository boardRepo;

    @Autowired
    private contentRepository contentRepo;

    public void createPost(Board board) {
//        for(Content content:board.getContentList()){
//            content.setBoard(board);
//        }
        //등록 시간 설정하고
        board.setRegistTime(LocalDate.now());
        //저장하기
        boardRepo.save(board);
    }

    public List<Board> getList() {
        return boardRepo.findAll();
    }

    public Board getOne(Long postId) {
        return boardRepo.getReferenceById(postId);
    }

    public void deleteOne(Long postId) {
        boardRepo.deleteById(postId);
    }

    public void updatePostInfo(Board board) {
        //바꿀 수 있는 것- genre, movieTitle,postTitle,thumbnail
        Optional<Board> entity = boardRepo.findById(board.getPostId());
        // ifPresent는 컨슈머를 매개변수로 입력받아서 객체가 존재할 때만 실행하는 Optional의 메소드
        entity.ifPresent(t ->{
            if(board.getGenre() != null) {
                t.setGenre(board.getGenre());
            }
            if(board.getMovieTitle() != null){
                t.setMovieTitle(board.getMovieTitle());
            }
            if(board.getPostTitle()!=null){
                t.setPostTitle(board.getPostTitle());
            }
            if(board.getThumbnail()!=null){
                t.setThumbnail(board.getThumbnail());
            }
            if(board.getContent()!=null){
                t.setContent(board.getContent());
            }
            // 이걸 실행하면 idx 때문에 update가 실행됨
            boardRepo.save(t);
        });
    }
/*
    public void updatePostContent(Board board){
        contentRepo.deleteByBoard(board); //다 지우고
        //새로 fk 설정한 다음에
        for(Content content:board.getContentList()){
            content.setBoard(board);
        }
        Collections.sort(board.getContentList(), Comparator.comparingInt(Content::getOrders));
        contentRepo.saveAll(board.getContentList()); //새로 저장


    }

 */

}

package com.ssafy.ReviewMoa_Spring.service.review;
import com.ssafy.ReviewMoa_Spring.dto.review.Board;
import com.ssafy.ReviewMoa_Spring.dto.review.Tag;
import com.ssafy.ReviewMoa_Spring.repository.review.boardRepository;
import com.ssafy.ReviewMoa_Spring.repository.review.tagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private tagRepository tagRepo;

    public void createPost(Board board) {
        //f.k 설정
        for(Tag tag:board.getTagList()){
            tag.setBoard(board);
        }
        //등록 시간 설정하고
        board.setRegistTime(LocalDate.now());
        //저장하기
        boardRepo.save(board);
    }

    //목록 역순 정렬(최신순 정렬)
    public Page<Board> getList(Pageable pageable,String genre,String search) {
        //모든 장르& 검색어 없음
        if(genre.equals("모든 장르")&&search.equals("")){
            return boardRepo.findAll(pageable);
        }
        //장르 있음& 검색어 없음
        else if(search.equals("")){
            return boardRepo.findAllByGenre(pageable,genre);
        }
        //장르 없음 & 검색어 있음
        else if(genre.equals("모든 장르")){
            return boardRepo.findByPostTitleContains(pageable,search);
        }
        //장르 있음 & 검색어 있음
        else{
            //todo 수정하기
            return boardRepo.findAll(pageable);
        }

        //return boardRepo.findAll(pageable );

    }
    //게시글 하나 반환
    public Board getOne(Long postId) {
        Board board=boardRepo.getReferenceById(postId);
        return board;
    }
    //조회수 갱신
    public void updateHit(Long postId){
        Optional<Board> entity = boardRepo.findById(postId);
        entity.ifPresent(t ->{
            int hit=t.getHit()+1;
            t.setHit(hit);
            boardRepo.save(t);
        });
    }
    //삭제
    public void deleteOne(Long postId) {
        boardRepo.deleteById(postId);
    }

    //수정
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
            updatePostContent(board);

            // 이걸 실행하면 idx 때문에 update가 실행됨
            boardRepo.save(t);
        });
    }



    public void updatePostContent(Board board){
        tagRepo.deleteByBoard(board); //다 지우고
        //새로 fk 설정한 다음에
        for(Tag tag:board.getTagList()){
            tag.setBoard(board);
        }
        //Collections.sort(board.getContentList(), Comparator.comparingInt(Tag::getOrders));
        tagRepo.saveAll(board.getTagList()); //새로 저장


    }


}

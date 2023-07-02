package com.bitc.java505_team4.service;

import com.bitc.java505_team4.dto.BoardDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
//    게시글 목록 불러오기
    List<BoardDTO> selectBoardList() throws Exception;


//    게시글 작성하기
    void insertBoard(BoardDTO board) throws Exception;

//    게시글 삭제하기
    void deleteBoard(int boardNum) throws Exception;

    void likeUpload(int boardNum) throws Exception;
}

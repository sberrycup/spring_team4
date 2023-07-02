package com.bitc.java505_team4.service;

import com.bitc.java505_team4.dto.BoardDTO;
import com.bitc.java505_team4.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<BoardDTO> selectBoardList() throws Exception {
        return boardMapper.selectBoardList();
    }

    @Override
    public void insertBoard(BoardDTO board) throws Exception {
        boardMapper.insertBoard(board);
    }

    @Override
    public void deleteBoard(int boardNum) throws Exception {
        boardMapper.deleteBoard(boardNum);
    }

    @Override
    public void likeUpload(int boardNum) throws Exception{
        boardMapper.likeUpdate(boardNum);
    }
}


































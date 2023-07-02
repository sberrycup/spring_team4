package com.bitc.java505_team4.controller;

import com.bitc.java505_team4.dto.BoardDTO;
import com.bitc.java505_team4.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

//    게시물 전체 목록 출력
    @RequestMapping(value = "/main/", method = RequestMethod.GET)
    public ModelAndView boardList() throws Exception {
        ModelAndView mv = new ModelAndView("main/mainPage");

        List<BoardDTO> boardList = boardService.selectBoardList();
//        List<BoardDTO> boardList = new ArrayList<>();
        mv.addObject("boardList", boardList);
        return mv;
    }

//게시글 댓글 가져오기


//    게시글 등록 (내부 프로세스)
    @RequestMapping(value = "/main/insert", method = RequestMethod.POST)
    public String boardInsertProcess(BoardDTO board) throws Exception {
        boardService.insertBoard(board);
        return "redirect:/main/";
    }

//    게시글 삭제
    @RequestMapping(value = "/main/{boardNum}", method = RequestMethod.DELETE)
    public String boardDelete(@PathVariable("boardNum") int boardNum) throws Exception {
        boardService.deleteBoard(boardNum);
        return "redirect:/main/";
    }
}















































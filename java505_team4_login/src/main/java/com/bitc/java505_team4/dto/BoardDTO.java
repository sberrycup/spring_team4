package com.bitc.java505_team4.dto;

import lombok.Data;

@Data
public class BoardDTO {
    private int boardNum;
    private String boardTitle;
    private String boardMemberName;
    private String boardContents;
    private String boardDate;
    private String boardFile;
    private String boardImg;
    private int boardLike;
}

package com.kosa.springcoffee.qnaboard.dto;

import com.kosa.springcoffee.qnareply.dto.QnaReplyDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QnaBoardDTO {

    private Long qnaBoardNo;
    private String title;
    private String content;
    private String regDate, modDate;
    private String writer;
    private String category;
    private List<QnaReplyDTO> replyList;
    private Boolean isAnswered;
}

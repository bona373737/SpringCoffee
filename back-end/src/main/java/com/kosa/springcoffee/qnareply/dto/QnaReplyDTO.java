package com.kosa.springcoffee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QnaReplyDTO {

    private Long qnaReplyNo;
    private String content;
    private String replyer;
    private Long qnaBoardNo;

}

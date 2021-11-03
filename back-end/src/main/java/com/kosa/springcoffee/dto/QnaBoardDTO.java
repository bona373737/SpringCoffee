package com.kosa.springcoffee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}

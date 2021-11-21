package com.kosa.springcoffee.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {

    private Long boardNo;
    private String title;
    private String content;
    private String writer;
    private String regDate, modDate;
    private String category;

}


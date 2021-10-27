package com.kosa.springcoffee.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {

    private Long noticeNo;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate, modDate;

}


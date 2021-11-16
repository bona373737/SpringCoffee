package com.kosa.springcoffee.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AttachImageDTO {
    /* 경로 */
    private String uploadPath;

    /* uuid */
    private String uuid;

    /* 파일 이름 */
    private String fileName;

    /* 상품 id */
    private Long itemNo;
}

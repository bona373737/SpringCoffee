package com.kosa.springcoffee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class QnaCategoryAnsweredPageRequestDTO {

    private int page;
    private int size;
    private String category;
    private Boolean isAnswered;

    public QnaCategoryAnsweredPageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page-1, size, sort);
    }

}

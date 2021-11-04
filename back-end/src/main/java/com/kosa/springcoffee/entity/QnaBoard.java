package com.kosa.springcoffee.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "sc_qna_board")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QnaBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaBoardNo;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    @Column(length = 50, nullable = false)
    private String category;

    @Column(columnDefinition = "boolean default false")
    private Boolean isAnswered;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}


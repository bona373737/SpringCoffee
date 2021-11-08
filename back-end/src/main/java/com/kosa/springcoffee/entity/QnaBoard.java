package com.kosa.springcoffee.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "sc_qna_board")
@Getter
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

    @Column(nullable = false)
    private Boolean isAnswered;

    @OneToMany(mappedBy = "qnaBoard", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    List<QnaReply> qnaReplies = new ArrayList<>();

    @Builder
    public QnaBoard(Long qnaBoardNo, String title, String content, Member writer, String category) {
        this.isAnswered = false; // default

        this.qnaBoardNo = qnaBoardNo;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.category = category;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeIsAnswered(Boolean isAnswered) {
        this.isAnswered = isAnswered;
    }
}

package com.kosa.springcoffee.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "sc_qna_reply")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QnaReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaReplyNo;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qna_board_no")
    private QnaBoard qnaBoard;

    public void changeContent(String content) {
        this.content = content;
    }
}

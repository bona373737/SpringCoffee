package com.kosa.springcoffee.repository;

import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.entity.QnaBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
public class QnaBoardRepositoryTest {

    @Autowired
    private QnaBoardRepository qnABoardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1,25).forEach(i -> {
            Optional<Member> result = memberRepository.findByEmail("user"+i+"@springCoffee.com" , false);
            Member member = result.get();
            QnaBoard qnaBoard = QnaBoard.builder()
                    .title("qna Title"+ i)
                    .content("qna Content" + i)
                    .writer(member)
                    .category("상품문의")
                    .build();
            System.out.println(qnABoardRepository.save(qnaBoard));
        });

        IntStream.rangeClosed(26,50).forEach(i -> {
            Optional<Member> result = memberRepository.findByEmail("user"+i+"@springCoffee.com" , false);
            Member member = result.get();
            QnaBoard qnaBoard = QnaBoard.builder()
                    .title("qna Title"+ i)
                    .content("qna Content" + i)
                    .writer(member)
                    .category("배송문의")
                    .build();
            System.out.println(qnABoardRepository.save(qnaBoard));
        });

        IntStream.rangeClosed(51,75).forEach(i -> {
            Optional<Member> result = memberRepository.findByEmail("user"+i+"@springCoffee.com" , false);
            Member member = result.get();
            QnaBoard qnaBoard = QnaBoard.builder()
                    .title("qna Title"+ i)
                    .content("qna Content" + i)
                    .writer(member)
                    .category("교환 및 반품문의")
                    .build();
            System.out.println(qnABoardRepository.save(qnaBoard));
        });

        IntStream.rangeClosed(76,100).forEach(i -> {
            Optional<Member> result = memberRepository.findByEmail("user"+i+"@springCoffee.com" , false);
            Member member = result.get();
            QnaBoard qnaBoard = QnaBoard.builder()
                    .title("qna Title"+ i)
                    .content("qna Content" + i)
                    .writer(member)
                    .category("기타")
                    .build();
            System.out.println(qnABoardRepository.save(qnaBoard));
        });
    }
}

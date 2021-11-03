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
        IntStream.rangeClosed(1,100).forEach(i -> {
            Optional<Member> result = memberRepository.findByEmail("user"+i+"@springCoffee.com" , false);
            Member member = result.get();
            QnaBoard qnaBoard = QnaBoard.builder()
                    .title("qna Title"+ i)
                    .content("qna Content" + i)
                    .writer(member)
                    .build();
            System.out.println(qnABoardRepository.save(qnaBoard));
        });
    }
}

package com.kosa.springcoffee.repository;

import com.kosa.springcoffee.member.MemberRepository;
import com.kosa.springcoffee.noticeboard.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

//    @Test
//    public void insertDummies() {
//        IntStream.rangeClosed(1,100).forEach(i -> {
//            Optional<Member> result = memberRepository.findByEmailOptional("user"+i+"@springCoffee.com");
//            Member member = result.get();
//            Board noticeBoard = Board.builder()
//                    .title("Title"+ i)
//                    .content("Content" + i)
//                    .writer(member)
//                    .category("notice")
//                    .build();
//            System.out.println(boardRepository.save(noticeBoard));
//        });
//        IntStream.rangeClosed(101,200).forEach(i -> {
//            Optional<Member> result = memberRepository.findByEmailOptional("user"+(i-100)+"@springCoffee.com");
//            Member member = result.get();
//            Board noticeBoard = Board.builder()
//                    .title("Title"+ i)
//                    .content("Content" + i)
//                    .writer(member)
//                    .category("qna")
//                    .build();
//            System.out.println(boardRepository.save(noticeBoard));
//        });
//        IntStream.rangeClosed(201,300).forEach(i -> {
//            Optional<Member> result = memberRepository.findByEmailOptional("user"+(i-200)+"@springCoffee.com");
//            Member member = result.get();
//            Board noticeBoard = Board.builder()
//                    .title("Title"+ i)
//                    .content("Content" + i)
//                    .writer(member)
//                    .category("coffee")
//                    .build();
//            System.out.println(boardRepository.save(noticeBoard));
//        });
//    }

//    @Test
//    public void updateTest() {
//        Optional<Board> result = boardRepository.findById(300L);
//
//        if (result.isPresent()) {
//            Board noticeBoard = result.get();
//
//            noticeBoard.changeTitle("Changed Title");
//            noticeBoard.changeContent("Changed Content");
//
//            boardRepository.save(noticeBoard);
//        }
//    }
}


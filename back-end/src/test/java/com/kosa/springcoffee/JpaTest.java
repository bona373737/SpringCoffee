package com.kosa.springcoffee;

import com.kosa.springcoffee.constant.UserRole;
import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.repository.MemberRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTest {

    @Autowired
    private MemberRepository accountRepository;

    @After
    public void cleanup() {
        /*
         * 이후 테스트 코드에 영향을 끼치지 않기 위해
         * 테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
         */
        accountRepository.deleteAll();
    }

    @Test
    public void userInsert() {
        //given
        accountRepository.save(
                Member.builder()
                        .username("qwe")
                        .password("qwe")
                        .role(UserRole.USER)
                        .build()
        );

        // when
        List<Member> userList = accountRepository.findAll();

        // then
        Member account = userList.get(0);
        assertThat(account.getUsername(), is("qwe"));
        assertThat(account.getPassword(), is("qwe"));
        assertThat(account.getRole(), is(UserRole.USER));
    }
}
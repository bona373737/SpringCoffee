package com.kosa.springcoffee.security;




import com.kosa.springcoffee.BackEndApplication;
import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.entity.MemberRole;
import com.kosa.springcoffee.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest(classes = BackEndApplication.class)
public class MemberTests {
    @Autowired
    private MemberRepository memberRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insert더미데이터(){
        //1~80 User
        //81~90 User,Manager
        //90~100 User,Manager, Admin
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user"+i+"@springCoffee.com")
                    .name("사용자"+i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1111"))
                    .build();

            //default role
            member.addMemberRole(MemberRole.USER);

            if(i>80){
                member.addMemberRole(MemberRole.MANAGER);
            }
            if(i>90){
                member.addMemberRole(MemberRole.ADMIN);
            }
            System.out.println(member.getEmail());
            System.out.println(member.getPassword());
            memberRepository.save(member);
        });

    }



    @Test
    public void testRead(){
        Optional<Member> result = memberRepository.findByEmail("user95@springCoffee.com" , false);
        Member member = result.get();

        System.out.println(member);
    }
}
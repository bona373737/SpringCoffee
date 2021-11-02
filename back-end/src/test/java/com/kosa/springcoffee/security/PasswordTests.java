package com.kosa.springcoffee.security;

import com.kosa.springcoffee.BackEndApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest(classes = BackEndApplication.class)
public class PasswordTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncode(){
        String pwd = "1111";
        String enPw = passwordEncoder.encode(pwd);
        System.out.println("enPw: " + enPw);

        boolean matchResult = passwordEncoder.matches(pwd, enPw);
        System.out.println(matchResult);


    }

}

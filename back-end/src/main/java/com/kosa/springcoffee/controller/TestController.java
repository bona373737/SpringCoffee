package com.kosa.springcoffee.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample")
public class TestController {
    @GetMapping("/all")
    public void exAll(){
        log.info("exAll--------------------------");
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/member")
    public void exMember(){
        log.info("exMember--------------------------");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public void exAdmin(){
        log.info("exAdmin--------------------------");
    }




}

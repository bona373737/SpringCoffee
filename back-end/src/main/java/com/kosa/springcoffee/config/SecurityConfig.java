package com.kosa.springcoffee.config;

import com.kosa.springcoffee.security.filter.ApiCheckFilter;
import com.kosa.springcoffee.security.filter.ApiLoginFilter;
import com.kosa.springcoffee.security.handler.ApiLoginFailHandler;
import com.kosa.springcoffee.security.util.JWTUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public ApiCheckFilter apiCheckFilter(){
//        return new ApiCheckFilter("/boards/**/*", jwtUtil());
//    }
//
//    @Bean
//    public JWTUtil jwtUtil(){
//        return new JWTUtil();
//    }
//
//    @Bean
//    public ApiLoginFilter apiLoginFilter() throws Exception{
//        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/login", jwtUtil());
//        apiLoginFilter.setAuthenticationManager(authenticationManager());
//
//        apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());
//        return apiLoginFilter;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/sample/all", "/signUp").permitAll();

        http.formLogin();
        http.csrf().disable();
        http.logout();

//        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

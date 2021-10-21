package com.kosa.springcoffee.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="member")
@Setter
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="member_id", unique = true, nullable = false)
    private Long id;

    private String email;
    private String pwd;
    private String name;
    private String address;
    private String phone;
//    private int grade;
//    private boolean isAdmin;
    
    // 주문내용 추가하기

}

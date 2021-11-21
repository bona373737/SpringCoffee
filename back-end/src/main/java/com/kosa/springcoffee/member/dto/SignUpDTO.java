package com.kosa.springcoffee.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {
    // member
    private String email;
    private String password;
    private String name;
    private String address;
    private boolean fromSocial;
    // isAdmin
    private Long isAdmin;
}

package com.kosa.springcoffee.dto;

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
    private boolean fromSocial;
    // isAdmin
    private Long isAdmin;
}

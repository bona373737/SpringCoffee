package com.kosa.springcoffee.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDTO {
    private String email;
    private String token;
}

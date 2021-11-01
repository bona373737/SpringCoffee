package com.kosa.springcoffee.dto.member;

import com.fasterxml.jackson.annotation.JsonInclude;

public class TokenDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;

    public TokenDTO(String token, String username) {
        super();
        this.token = token;
        this.username = username;
    }
}

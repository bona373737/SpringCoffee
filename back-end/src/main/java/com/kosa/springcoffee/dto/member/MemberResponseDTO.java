package com.kosa.springcoffee.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDTO {

    private String username;

    private String role;

    public MemberResponseDTO(String username, String role) {
        super();
        this.username = username;
        this.role = role;
    }
}

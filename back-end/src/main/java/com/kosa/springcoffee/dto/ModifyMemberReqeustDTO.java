package com.kosa.springcoffee.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModifyMemberReqeustDTO {
    private String email;
    private String password;
    private String name;
    private String address;
}

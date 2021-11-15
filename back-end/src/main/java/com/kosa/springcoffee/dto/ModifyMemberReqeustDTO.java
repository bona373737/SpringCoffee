package com.kosa.springcoffee.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class ModifyMemberReqeustDTO {
    private String email;
    private String password;
    private String name;
    private String address;
}

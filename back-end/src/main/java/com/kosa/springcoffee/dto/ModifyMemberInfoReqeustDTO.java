package com.kosa.springcoffee.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModifyMemberInfoReqeustDTO {
    private String email;
    private String curPassword;
    private String newPassword;
    private String name;
    private String address;
}

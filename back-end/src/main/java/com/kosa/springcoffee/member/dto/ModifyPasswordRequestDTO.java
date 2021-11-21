package com.kosa.springcoffee.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModifyPasswordRequestDTO {
    private String email;
    private String curPassword;
    private String newPassword;
}

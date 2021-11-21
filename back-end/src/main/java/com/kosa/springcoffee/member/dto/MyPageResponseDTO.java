package com.kosa.springcoffee.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyPageResponseDTO {
    private String email;
    private String name;
    private String address;
}

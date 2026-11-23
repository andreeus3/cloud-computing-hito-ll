package com.ugr.muii.userservice.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class LoginResponseDto {
    private String token;
    private String type = "Bearer";
}

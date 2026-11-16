package com.ugr.muii.userservice.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UpdateUserDto {

    private String name;
    private String email;
    private String phone;
}

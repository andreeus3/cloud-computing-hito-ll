package com.ugr.muii.userservice.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserDto {

    private String name;
    private String email;
    private String username;
    private String password;
    private String phone;
    private Boolean active = false;

}

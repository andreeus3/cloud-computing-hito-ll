package com.ugr.muii.userservice.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

@Component
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Boolean active;
    private LocalDateTime createdAt;
}

package com.ugr.muii.userservice.Service;

import com.ugr.muii.userservice.Dto.LoginRequestDto;
import com.ugr.muii.userservice.Dto.LoginResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto dto);
}

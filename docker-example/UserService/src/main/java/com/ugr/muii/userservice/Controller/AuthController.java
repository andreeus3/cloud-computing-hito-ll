package com.ugr.muii.userservice.Controller;

import com.ugr.muii.userservice.Dto.LoginRequestDto;
import com.ugr.muii.userservice.Dto.LoginResponseDto;
import com.ugr.muii.userservice.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login (
            @RequestBody LoginRequestDto loginRequestDto){

        LoginResponseDto loginResponseDto = authService.login(loginRequestDto);

        return ResponseEntity.ok(loginResponseDto);
    }
}

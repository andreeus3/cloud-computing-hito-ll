package com.ugr.muii.userservice.Service;

import com.ugr.muii.userservice.Dao.UserDao;
import com.ugr.muii.userservice.Dto.LoginRequestDto;
import com.ugr.muii.userservice.Dto.LoginResponseDto;
import com.ugr.muii.userservice.Entity.User;
import com.ugr.muii.userservice.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {

        User user = userDao.findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                "Credenciales incorrectas"));

        if(!user.getActive()){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Credenciales incorrectas");
        }

        if(!passwordEncoder.matches(
                dto.getPassword(), user.getPassword()
        )){
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Credenciales incorrectas");
        }

        String token = jwtService.generatedToken(user.getEmail());

        LoginResponseDto loginResponseDto = new LoginResponseDto();

        loginResponseDto.setToken(token);

        return loginResponseDto;

    }
}

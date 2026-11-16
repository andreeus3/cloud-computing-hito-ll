package com.ugr.muii.userservice.Service;

import com.ugr.muii.userservice.Dto.CreateUserDto;
import com.ugr.muii.userservice.Dto.UpdateUserDto;
import com.ugr.muii.userservice.Dto.UserDto;
import com.ugr.muii.userservice.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto addUser(CreateUserDto addUser);
    List<UserDto> findAllUsers();
    Optional<UserDto> getUserById(Long id);
    UserDto updateUser(Long id, UpdateUserDto updateUserDto);
    void deleteUser(Long id);

}

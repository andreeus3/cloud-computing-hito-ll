package com.ugr.muii.userservice.Service;

import com.ugr.muii.userservice.Dao.UserDao;
import com.ugr.muii.userservice.Dto.CreateUserDto;
import com.ugr.muii.userservice.Dto.UpdateUserDto;
import com.ugr.muii.userservice.Dto.UserDto;
import com.ugr.muii.userservice.Entity.User;
import com.ugr.muii.userservice.Mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private  final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserDto userDto;


    @Override
    public UserDto addUser(CreateUserDto addUser) {
        if (userDao.existsByEmail(addUser.getEmail())) {
            throw new RuntimeException("Email already Exists");
        }
        User user = new User();

        user.setName(addUser.getName());
        user.setPhone(addUser.getPhone());
        user.setPassword(
                passwordEncoder.encode(addUser.getPassword())
        );
        user.setEmail(addUser.getEmail());
        user.setActive(addUser.getActive());


        User userSaved = userDao.save(user);
        return userMapper.toDto(userDao.save(userSaved));
    }

    @Override
    public List<UserDto> findAllUsers() {

        List<User> getAllUser = userDao.findAll().stream().toList();
        List<UserDto> getAllUserDto = new ArrayList<>();

        for(User user : getAllUser){
            getAllUserDto.add(userMapper.toDto(user));
        }

        return getAllUserDto;
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        Optional<User> getUserById = userDao.findById(id);

        if(getUserById.isEmpty()){
            return Optional.empty();
        }

        User user = getUserById.get();

        return Optional.of(userMapper.toDto(user));

    }

    @Override
    public UserDto updateUser(Long id, UpdateUserDto userDto) {
        User userExisting = userDao.findById(id).
                orElseThrow(
                        ()->
                                new RuntimeException("User not Found"));


        userMapper.updateEntity(userExisting, userDto);

        User updatedUser = userDao.save(userExisting);

        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }
}

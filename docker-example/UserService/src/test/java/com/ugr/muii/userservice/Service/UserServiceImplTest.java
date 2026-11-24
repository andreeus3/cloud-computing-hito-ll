package com.ugr.muii.userservice.Service;


import com.ugr.muii.userservice.Dao.UserDao;
import com.ugr.muii.userservice.Dto.CreateUserDto;
import com.ugr.muii.userservice.Dto.UpdateUserDto;
import com.ugr.muii.userservice.Dto.UserDto;
import com.ugr.muii.userservice.Entity.User;
import com.ugr.muii.userservice.Mapper.UserMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void findAllUsersTest() {

        // preparar los datos
        when(userDao.findAll())
                .thenReturn(java.util.Collections.emptyList());

        var result = userService.findAllUsers();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(userDao, times(1)).findAll();
    }

    @Test
    void findAllUsers_WithUsers_Test() {

        User user1 = new User();
        user1.setId(1L);
        user1.setName("Andres");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Carlos");

        List<User> users = List.of(user1, user2);

        UserDto dto1 = new UserDto();
        dto1.setId(1L);
        dto1.setName("Andres");

        UserDto dto2 = new UserDto();
        dto2.setId(2L);
        dto2.setName("Carlos");

        when(userDao.findAll()).thenReturn(users);

        when(userMapper.toDto(user1)).thenReturn(dto1);
        when(userMapper.toDto(user2)).thenReturn(dto2);

        List<UserDto> result = userService.findAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals("Andres", result.get(0).getName());
        assertEquals("Carlos", result.get(1).getName());

        verify(userDao, times(1)).findAll();
    }

    @Test
    public void addUser(){
        CreateUserDto createUserDto = new CreateUserDto();

        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("Ivan");
        userDto.setEmail("ivan@gmail.com");

        createUserDto.setEmail("ivannn@gmail.com");
        createUserDto.setPassword("123456");
        createUserDto.setName("Pruebas");
        createUserDto.setActive(true);


        when(userMapper.toDto(any(User.class)))
                .thenReturn(userDto);

        when(passwordEncoder.encode("123456"))
                .thenReturn("passwordEncrypted");

        when(userDao.findByEmail(createUserDto.getEmail()))
                .thenReturn(Optional.empty());

        when(userDao.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserDto result = userService.addUser(createUserDto);

        assertNotNull(result);

        verify(passwordEncoder).encode("123456");

        verify(userDao, times(1)).findByEmail(createUserDto.getEmail());

    }

    @Test
    public void getUserById(){

        Long id = 1L;

        User userFound = new User();

        userFound.setEmail("test@gmail.com");
        userFound.setName("Testing");
        userFound.setId(1L);
        userFound.setPhone("536765778");
        userFound.setCreatedAt(LocalDateTime.now());

        when(userDao.findById(id))
                .thenReturn(Optional.of(userFound));

        UserDto userDto = new UserDto();

        userDto.setId(id);
        userDto.setName("Testing");
        userDto.setEmail("test@gmail.com");
        userDto.setPhone("536765778");

        when(userMapper.toDto(userFound))
                .thenReturn(userDto);

        Optional<UserDto> result = userService.getUserById(id);

        assertTrue(result.isPresent());

        assertEquals(id, result.get().getId());

        assertEquals("Testing", result.get().getName());
        assertEquals("test@gmail.com", result.get().getEmail());

        verify(userDao, times(1)).findById(id);

    }

    @Test
    public void getUserById_Empty() {

        Long id = 1L;

        when(userDao.findById(id))
                .thenReturn(Optional.empty());

        userService.getUserById(id);

        verify(userDao, times(1)).findById(id);

    }

    @Test
    public void updateUser(){
        Long id = 1L;

        UpdateUserDto userDto = new UpdateUserDto();
        userDto.setName("Testing actualizado");
        userDto.setEmail("nuevo@gmail.com");
        userDto.setPhone("536765778");

        User userFound = new User();
        userFound.setId(id);
        userFound.setName("Testing");
        userFound.setEmail("test@gmail.com");

        when(userDao.findById(id))
                .thenReturn(Optional.of(userFound));

        when(userDao.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        userService.updateUser(id, userDto);

        verify(userDao, times(1)).findById(id);

        verify(userMapper, times(1))
                .updateEntity(any(User.class), any(UpdateUserDto.class));

        verify(userDao, times(1))
                .save(any(User.class));
    }

    @Test
    public void updateUser_notFound(){
        Long id = 99L;

        UpdateUserDto userDto = new UpdateUserDto();
        userDto.setName("Testing");

        when(userDao.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> userService.updateUser(id, userDto)
        );

        verify(userDao, times(1)).findById(id);
        verify(userDao, never()).save(any(User.class));
    }

    @Test
    void deleteUser() {
        Long id = 1L;

        userService.deleteUser(id);

        verify(userDao, times(1)).deleteById(id);
    }
}
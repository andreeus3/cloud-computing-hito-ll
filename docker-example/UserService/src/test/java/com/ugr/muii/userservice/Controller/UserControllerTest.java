package com.ugr.muii.userservice.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ugr.muii.userservice.Dto.CreateUserDto;
import com.ugr.muii.userservice.Dto.UserDto;
import com.ugr.muii.userservice.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }

    @Test
    void findAllUsers() throws Exception{

        UserDto userDto = new UserDto();

        userDto.setId(1L);
        userDto.setActive(false);
        userDto.setName("Pruebas");
        userDto.setEmail("pruebas@gmail.com");
        userDto.setPhone("254625778");

        List<UserDto> userDtoList = List.of(userDto);

        when(userService.findAllUsers())
                .thenReturn(userDtoList);

        mockMvc.perform(get("/user/allUsers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Pruebas"))
                .andExpect(jsonPath("$.[0].email").value("pruebas@gmail.com"))
                .andExpect(jsonPath("$.[0].phone").value("254625778"));

        verify(userService).findAllUsers();


    }

    @Test
    void findUserById()throws Exception{
        Long id = 1L;

        UserDto userDto = new UserDto();

        userDto.setId(id);
        userDto.setEmail("pruebas@gmail.com");
        userDto.setPhone("627235667");
        userDto.setName("test user");

        when(userService.getUserById(id)).thenReturn(Optional.of(userDto));

        mockMvc.perform(get("/user/findById/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("test user"))
                .andExpect(jsonPath("$.email").value("pruebas@gmail.com"))
                .andExpect(jsonPath("$.phone").value("627235667"));


        verify(userService).getUserById(id);

    }

    @Test
    void getUserById_notFound()throws Exception{
        Long id = 99L;

        when(userService.getUserById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/user/findById/{id}", id))
                .andExpect(status().isNotFound());

        verify(userService).getUserById(id);
    }

    @Test
    void addUser() throws Exception{
        Long id = 1L;

        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setName("pruebas");
        createUserDto.setEmail("pruebas@gmail.com");
        createUserDto.setPassword("123456");
        createUserDto.setPhone("601234567");

        UserDto userDto = new UserDto();

        userDto.setId(id);
        userDto.setName("pruebas");
        userDto.setEmail("pruebas@gmail.com");
        userDto.setActive(true);

        when(userService.addUser(any(CreateUserDto.class)))
                .thenReturn(userDto);

        mockMvc.perform(post("/user/add-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserDto))).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("pruebas"))
                .andExpect(jsonPath("$.email").value("pruebas@gmail.com"));

        verify(userService, times(1))
                .addUser(any(CreateUserDto.class));
    }

    @Test
    void deleteUser() throws Exception{

        Long id = 1L;

        mockMvc.perform(delete("/user/delete-user/{id}", id))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(id);
    }
}

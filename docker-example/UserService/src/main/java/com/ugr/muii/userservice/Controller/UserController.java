package com.ugr.muii.userservice.Controller;

import com.ugr.muii.userservice.Dto.CreateUserDto;
import com.ugr.muii.userservice.Dto.UpdateUserDto;
import com.ugr.muii.userservice.Dto.UserDto;
import com.ugr.muii.userservice.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDto>> findAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable("id") Long id){
        Optional<UserDto> userDtoOptional = userService.getUserById(id);

        return userDtoOptional.map(ResponseEntity::ok)
                .orElseGet(()->
                        ResponseEntity
                                .notFound()
                                .build());
    }

    @PatchMapping("update-user/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("id") Long id,
            @RequestBody UpdateUserDto userDto) {

        UserDto updateUser = userService.updateUser(id, userDto);

        return ResponseEntity.ok(updateUser);

    }


    @PostMapping("/add-user")
    public ResponseEntity addUser(@RequestBody CreateUserDto createUserDto){
        UserDto userDto = userService.addUser(createUserDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }


    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(
                HttpStatus.NO_CONTENT)
                .body("Eliminado correctamente"
                );
    }


}

package com.example.testProjectW.controller;

import com.example.testProjectW.Repository.UserRepository;
import com.example.testProjectW.entity.User;
import com.example.testProjectW.exception.NotFoundException;
import com.example.testProjectW.exception.ValidateException;
import com.example.testProjectW.model.user.UserRequestDto;
import com.example.testProjectW.model.user.UserResponseDto;
import com.example.testProjectW.service.UserService;
import com.example.testProjectW.validate.UserValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@ControllerAdvice
public class UserController {

    @Autowired
    UserValidate userValidate;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/listAll")
    public List<User> listUser() throws NotFoundException {
        List<User> users = userService.listAll();

        return users;

    }

    @GetMapping(value = {"/getUser/{accntNo}"})
    public User getUserByAccntNo(@PathVariable String accntNo) throws NotFoundException {
        User userByAccntNo = userService.getUserByAccntNo(accntNo);

        return userByAccntNo;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto) throws Exception {

        userValidate.validateUser(requestDto);

        User user = userService.createUser(requestDto);

        UserResponseDto response = userService.composeResponse(user);

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update/{accntNo}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable String accntNo, @RequestBody UserRequestDto requestDto) throws NotFoundException, ValidateException {
        User user = userValidate.vildaterUpdate(accntNo, requestDto);
        User updated = userService.updateUser(requestDto, user);

        UserResponseDto response = userService.composeResponse(updated);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/delete/{accntNo}")
    public void deleteUserByAccntNo(@PathVariable String accntNo) throws Exception {

        userService.deleteUserById(accntNo);
    }

}

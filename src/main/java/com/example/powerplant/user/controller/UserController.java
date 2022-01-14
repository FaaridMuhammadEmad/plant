package com.example.powerplant.user.controller;

import com.example.powerplant.user.dto.RegisterUserDto;
import com.example.powerplant.user.service.IUserService;
import com.example.powerplant.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/1.0/user")
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping()
    public ResponseEntity registerUser(@Valid @RequestBody RegisterUserDto registerUserDto){
        Message message = userService.registerUser(registerUserDto);
        return ResponseEntity.status(message.getCode()).body(message);
    }
}

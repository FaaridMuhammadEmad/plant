package com.example.powerplant.user.service;

import com.example.powerplant.user.dto.RegisterUserDto;
import com.example.powerplant.util.Message;

public interface IUserService {
    public Message registerUser(RegisterUserDto userDTO);
}

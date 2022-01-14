package com.example.powerplant.user.service;

import com.example.powerplant.global.ConstantValues;
import com.example.powerplant.user.dto.RegisterUserDto;
import com.example.powerplant.user.model.User;
import com.example.powerplant.user.repository.UserRepository;
import com.example.powerplant.util.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.regex.Pattern;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByEmail(username);
        if (user == null) {
            return null;
        }
        ArrayList<GrantedAuthority> l = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), true, true, true,
                true, l);
    }

    @Override
    public Message registerUser(RegisterUserDto userDTO) {
        Message message = new Message();
        try {
            if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty() ||
                    userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
                message.setCode(ConstantValues.SERVICE_BAD_REQUEST).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("Bad Request");
            } else {
                Pattern emailPattern = Pattern.compile(ConstantValues.emailRegex, Pattern.CASE_INSENSITIVE);
                Pattern passwordPattern = Pattern.compile(ConstantValues.passwordRegex);
                if (!emailPattern.matcher(userDTO.getEmail()).matches()) {
                    message.setCode(ConstantValues.SERVICE_BAD_REQUEST).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("Invalid Email");
                } else if (!passwordPattern.matcher(userDTO.getPassword()).matches()) {
                    message.setCode(ConstantValues.SERVICE_BAD_REQUEST).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("Invalid Password");
                } else {
                    if (userRepository.findOneByEmail(userDTO.getEmail()) != null) {
                        message.setMessage("Email already exists").setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setCode(ConstantValues.SERVICE_BAD_REQUEST);
                    } else {
                        User user = new User();
                        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                            String hash = new BCryptPasswordEncoder().encode(userDTO.getPassword());
                            user.setPassword(hash);
                        }
                        user.setEmail(userDTO.getEmail());

                        userRepository.save(user);

//                        HttpResponse<String> response = Unirest.post("http://localhost:8081/oauth/token")
//                                .header("authorization", "Basic c29jb2w6c2VjcmV0")
//                                .header("cache-control", "no-cache")
//                                .header("postman-token", "cad8d1b7-19cd-5682-25c1-a8c4bee73228")
//                                .field("grant_type", "password")
//                                .field("username", userDTO.getEmail())
//                                .field("password", userDTO.getPassword())
//                                .asString();
//
//                        JSONParser parser = new JSONParser();
//                        JSONObject json = (JSONObject) parser.parse(response.getBody());
                        message.setMessage("User created successfully").setStatus(ConstantValues.SERVICE_SUCCESS_STATUS).setData(null).setCode(ConstantValues.SERVICE_OK);
                    }
                }
            }
        } catch (Exception e) {
            return message.setCode(ConstantValues.SERVICE_INTERNAL_SERVER).setStatus(ConstantValues.SERVICE_UNSUCCESS_STATUS).setMessage("Oops! Something went wrong!!!").setData(e.getCause().getMessage());
        }
        return message;
    }

}

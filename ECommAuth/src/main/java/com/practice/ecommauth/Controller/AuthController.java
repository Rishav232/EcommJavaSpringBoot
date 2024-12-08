package com.practice.ecommauth.Controller;

import com.practice.ecommauth.DTO.*;
import com.practice.ecommauth.Exceptions.InvalidUserException;
import com.practice.ecommauth.Exceptions.UserAlreadyExists;
import com.practice.ecommauth.Models.User;
import com.practice.ecommauth.Service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    IAuthService iAuthService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupDto signupDto)
    {
        if(signupDto.getEmail().isEmpty()||signupDto.getPassword().isEmpty())
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        try {

            User user = iAuthService.signup(signupDto.getEmail(), signupDto.getPassword());
            return new ResponseEntity<>(userDtoFromUser(user),HttpStatus.CREATED);
        }catch (UserAlreadyExists userAlreadyExists)
        {
            throw new RuntimeException(userAlreadyExists.getMessage());
        }

    }
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto)
    {
        try {
            Pair<User, MultiValueMap<String,String>> response = iAuthService.login(loginDto.getEmail(), loginDto.getPassword());
            if(response==null)
                return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);

            return new ResponseEntity<>(userDtoFromUser(response.getFirst()),response.getSecond(),HttpStatus.OK);
        }catch (InvalidUserException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<UserDto> logout(@RequestBody LogoutDto logoutDto)
    {
        try {
            User user = iAuthService.logout(logoutDto.getEmail());
            return new ResponseEntity<>(userDtoFromUser(user),HttpStatus.OK);
        }catch (InvalidUserException e)
        {
            throw new RuntimeException(e.getMessage());
        }

    }
    @PostMapping("/validateToken")
    public Boolean validateToken(@RequestBody SessionDto sessionDto)
    {
        return iAuthService.validateToken(sessionDto.getToken(), sessionDto.getId());
    }


    private UserDto userDtoFromUser(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());

        return userDto;
    }
}

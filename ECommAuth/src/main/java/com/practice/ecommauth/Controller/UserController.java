package com.practice.ecommauth.Controller;

import com.practice.ecommauth.DTO.UserDto;
import com.practice.ecommauth.Models.User;
import com.practice.ecommauth.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{userId}")
    public UserDto getUserbyId(@PathVariable Long userId)
    {
        return userDtoFromUser(userService.getUserbyId(userId));
    }

    private UserDto userDtoFromUser(User user)
    {
        UserDto userDto = new UserDto();
        user.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());

        return userDto;
    }
}

package com.practice.ecommauth.Service;

import com.practice.ecommauth.Models.User;
import com.practice.ecommauth.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserbyId(Long id)
    {
        return userRepository.findById(id).orElse(null);
    }
}

package com.practice.ecommauth.Service;

import com.practice.ecommauth.Models.User;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;


public interface IAuthService {

    User signup(String email,String password);
    Pair<User, MultiValueMap<String,String>> login(String email, String password);
    Boolean validateToken(String token,Long Id);
    User logout(String email);
}

package com.practice.ecommauth.Security;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.practice.ecommauth.Models.User;
import com.practice.ecommauth.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new CustomUserDetails(userOptional.get());

    }
}

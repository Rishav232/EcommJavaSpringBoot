package com.practice.ecommauth.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.ecommauth.DTO.EmailDto;
import com.practice.ecommauth.Exceptions.InvalidUserException;
import com.practice.ecommauth.Exceptions.UserAlreadyExists;
import com.practice.ecommauth.Exceptions.UserAlreadyLoggedIn;
import com.practice.ecommauth.Models.Session;
import com.practice.ecommauth.Models.State;
import com.practice.ecommauth.Models.User;
import com.practice.ecommauth.Repository.SessionRepository;
import com.practice.ecommauth.Repository.UserRepository;
import com.practice.ecommauth.client.KafkaClient;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService implements IAuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SecretKey secretKey;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaClient kafkaClient;

    @Override
    public User signup(String email, String password)throws UserAlreadyExists {
        Optional<User> user  = userRepository.findByEmail(email);

        if(user.isPresent())
            throw new UserAlreadyExists("User Already Exists");

        User responseUser = new User();
        responseUser.setEmail(email);
        responseUser.setState(State.ACTIVE);
        responseUser.setPassword(bCryptPasswordEncoder.encode(password));


        try {
            String topic = "signup";
            EmailDto emailDto = new EmailDto();
            emailDto.setTo(email);
            emailDto.setFrom("rishavdummy2321@gmail.com");
            emailDto.setBody("This is a test email");
            emailDto.setSubject("Test body");
            String message = objectMapper.writeValueAsString(emailDto);
            kafkaClient.sendMessage(topic,message);

        }catch (JsonProcessingException e)
        {
            throw new RuntimeException(e.getMessage());
        }


        return userRepository.save(responseUser);
    }

    @Override
    public Pair<User,MultiValueMap<String,String>> login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent())
        {

            User user = userOptional.get();
            if(!bCryptPasswordEncoder.matches(password, user.getPassword()))
                throw new InvalidUserException("Email or Password incorrect");

            Map<String,Object> claim = new HashMap<>();
            claim.put("user_id",user.getId());
            claim.put("email",user.getEmail());
            claim.put("iss","Rishav Subedar");
            Long currTime = System.currentTimeMillis();
            claim.put("iat",currTime);
            claim.put("exp",currTime+86400000);

            MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();

//            MacAlgorithm algorithm = Jwts.SIG.HS256;
//            SecretKey secretKey = algorithm.key().build();
            String token = Jwts.builder().claims(claim).signWith(secretKey).compact();

            headers.add(HttpHeaders.SET_COOKIE,token);
            Pair<User,MultiValueMap<String,String>> p = Pair.of(user, headers);

            Session session = new Session();
            session.setUser(user);
            session.setToken(token);
            session.setState(State.ACTIVE);
            sessionRepository.save(session);

            return p;
        }
        return null;

    }

    public Boolean validateToken(String token,Long Id)
    {
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token,Id);
        if(sessionOptional.isEmpty())
            return false;

        JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
        Claims claims = jwtParser.parseSignedClaims(token).getPayload();

        Long expiryTime = (Long) claims.get("exp");
        Long currTime = System.currentTimeMillis();

        if(currTime > expiryTime)
            throw new RuntimeException("Token Expired");

        return true;

    }
    @Override
    @Transactional
     public User logout(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty())
            throw new InvalidUserException("Email or Password is incorrect");

        User user = userOptional.get();
        Optional<Session> sessionOptional = sessionRepository.findByUser_Id(user.getId());
        if(sessionOptional.isEmpty())
            throw new InvalidUserException("Please Log in again");


        sessionRepository.deleteByTokenAndUser_Id(sessionOptional.get().getToken(),user.getId());

        return user;
    }
}

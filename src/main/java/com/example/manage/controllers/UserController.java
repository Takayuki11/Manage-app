package com.example.manage.controllers;

import com.example.manage.JWTProvider;
import com.example.manage.configuration.dto.UserSignUpRequest;
import com.example.manage.service.UserService;
import com.example.manage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/users")
public class UserController {

    public UserService userService;
    public PasswordEncoder passwordEncoder;
    public JWTProvider provider;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder, JWTProvider provider){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.provider = provider;
    }

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpServletResponse response, HttpServletRequest request){
        User loginUser = this.userService.getUserByEmailWithPassword(user);

        if(loginUser != null){
            String token = this.provider.createToken(loginUser);
            response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            response.setStatus(HttpStatus.OK.value());
            return token;
        } else {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            throw new UsernameNotFoundException("User Not Found");
        }
    }

    @PostMapping("/create")
    public void create(@RequestBody UserSignUpRequest request){
        String password = this.passwordEncoder.encode(request.getPassword());
        User user = new User(request.getName(), request.getEmail(), password, request.getRole(), request.getCompanyId());
        this.userService.save(user);
    }
}

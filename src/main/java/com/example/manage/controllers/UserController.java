package com.example.manage.controllers;

import com.example.manage.JWTProvider;
import com.example.manage.service.UserService;
import com.example.manage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/signup")
    public void signup(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userService.save(user);
    }

    @PostMapping("/login")
    public void login(@RequestBody User user, HttpServletResponse response){
        User loginUser = this.userService.getUserByEmailWithPassword(user);

        if(loginUser != null){
            String token = this.provider.createToken(loginUser);
            response.setHeader("Authorization", token);
            response.setStatus(HttpStatus.OK.value());
        } else {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            throw new UsernameNotFoundException("User Not Found");
        }
    }
}

package com.example.manage.service;

import com.example.manage.entity.User;
import com.example.manage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    public UserRepository userRepository;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User user) {
        this.userRepository.save(user);
    }

    public User getUserByEmailWithPassword(User user){
        User loginUser = userRepository.findByEmail(user.getEmail());
        System.out.println(loginUser.getEmail());
        System.out.println(loginUser);
        if (passwordEncoder.matches(user.getPassword(), loginUser.getPassword())) {
            return loginUser;
        }
        return null;
    }
}

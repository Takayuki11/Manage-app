package com.example.manage.controllers;

import com.example.manage.configuration.dto.MasterUserSignUpRequest;
import com.example.manage.entity.Company;
import com.example.manage.entity.User;
import com.example.manage.service.CompanyService;
import com.example.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/company")
public class CompanyController {

    public final CompanyService companyService;
    public final UserService userService;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public CompanyController(CompanyService companyService, UserService userService, PasswordEncoder passwordEncoder){
        this.companyService = companyService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/create")
    @Transactional
    public void create(@RequestBody MasterUserSignUpRequest data){
        Company company = new Company(data.getCompanyName());
        this.companyService.save(company);
        String password = this.passwordEncoder.encode(data.getPassword());
        User user = new User(data.getName(), data.getEmail(), password, "master", company.getId());
        this.userService.save(user);
    }
}

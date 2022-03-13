package com.example.manage.controllers;

import com.example.manage.JsonData;
import com.example.manage.entity.Company;
import com.example.manage.entity.User;
import com.example.manage.service.CompanyService;
import com.example.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/company")
public class CompanyController {

    public final CompanyService companyService;
    public final UserService userService;

    @Autowired
    public CompanyController(CompanyService companyService, UserService userService){
        this.companyService = companyService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public void create(@RequestBody JsonData data){
//        明日質問する　データの型とカンパニーID
        Company company = new Company();
        company.setCompanyName(data.getCompanyName());
        this.companyService.save(company);
        User user = new User();
        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());
        user.setCompanyId(company.getId());
        user.setRole("master");
        this.userService.save(user);
    }
}

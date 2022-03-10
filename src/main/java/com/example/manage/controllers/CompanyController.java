package com.example.manage.controllers;

import com.example.manage.entity.Company;
import com.example.manage.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    public final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService service){
        this.companyService = service;
    }

    @PostMapping("/create")
    public void create(@RequestBody Company company){
        this.companyService.save(company);
    }
}

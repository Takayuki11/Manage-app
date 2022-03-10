package com.example.manage.service;

import com.example.manage.entity.Company;
import com.example.manage.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    public final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository repository){
        this.companyRepository = repository;
    }

    public void save(Company company){
        this.companyRepository.save(company);
    }
}

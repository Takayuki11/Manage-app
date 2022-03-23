package com.example.manage.configuration.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpRequest {
    private String name;
    private String email;
    private String password;
    private String role;
    private int companyId;
}

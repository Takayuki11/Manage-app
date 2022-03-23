package com.example.manage.configuration.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class MasterUserSignUpRequest {
    private String companyName;
    private String name;
    private String email;
    private String password;
}

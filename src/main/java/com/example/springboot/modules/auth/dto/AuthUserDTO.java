package com.example.springboot.modules.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthUserDTO {
    private String username;
    private String password;
}

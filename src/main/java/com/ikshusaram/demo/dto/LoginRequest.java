package com.ikshusaram.demo.dto;

import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;
public class LoginRequest implements Serializable {
    
    public LoginRequest() {}

    @NotBlank(message = "email must not be blank")
    public String email;

    @NotBlank(message = "password must not be blank")
    public String password;

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}

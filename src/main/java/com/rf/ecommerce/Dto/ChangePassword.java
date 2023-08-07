package com.rf.ecommerce.Dto;

import jakarta.persistence.Column;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data

public class ChangePassword {

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,}$" ,message = "Lütfen en az bir büyük harf,bir küçüj harf ve sayi kullanin")
    private String password;
    @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    @Column(unique = true)
    private String email;

}

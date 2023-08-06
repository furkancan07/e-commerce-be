package com.rf.ecommerce.Entity.Admin;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Table(name = "adminler")
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(unique = true)
    @NotNull
    private String username;
    @NotNull
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,}$" ,message = "Lütfen en az bir büyük harf,bir küçüj harf ve sayi kullanin")
    private String password;
}

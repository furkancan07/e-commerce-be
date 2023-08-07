package com.rf.ecommerce.Entity.Product;

import com.rf.ecommerce.Entity.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Table
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
    private String content;
    @ManyToOne
    @JoinColumn(name = "Urun")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "users")
    private User user;

}

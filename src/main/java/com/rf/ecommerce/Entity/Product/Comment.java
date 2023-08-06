package com.rf.ecommerce.Entity.Product;

import com.rf.ecommerce.Entity.User.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "Urun")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "users")
    private User user;

}

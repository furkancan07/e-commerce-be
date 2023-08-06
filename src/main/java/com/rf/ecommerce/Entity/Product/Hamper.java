package com.rf.ecommerce.Entity.Product;

import com.rf.ecommerce.Entity.User.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table
@Entity
public class Hamper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
}

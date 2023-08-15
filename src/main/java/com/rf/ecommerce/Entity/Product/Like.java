package com.rf.ecommerce.Entity.Product;

import com.rf.ecommerce.Entity.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name = "Beğeni")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int count;
    @ManyToOne
    private Product product;
    @ManyToOne
    private User user;

}

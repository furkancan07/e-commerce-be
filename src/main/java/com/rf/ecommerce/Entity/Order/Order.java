package com.rf.ecommerce.Entity.Order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rf.ecommerce.Entity.Product.Product;
import com.rf.ecommerce.Entity.User.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
// simülasyon
@Entity
@Data
@Table
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private long timestap=new Date().getTime();

    private String orderStatus;
}

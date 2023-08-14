package com.rf.ecommerce.Entity.Order;

import com.rf.ecommerce.Entity.Product.Product;
import com.rf.ecommerce.Entity.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;
// simülasyon
@Entity
@Data
@Table(name = "Siparişler")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Product product;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private User user;

    private long timestap=new Date().getTime();

    private String orderStatus;
    private String address;


    private String city;

    private String district;

    private int postCode;
}

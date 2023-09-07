package com.rf.ecommerce.Dto.Product;

import com.rf.ecommerce.Dto.User.UserDto;
import com.rf.ecommerce.Entity.Product.Product;
import com.rf.ecommerce.Entity.User.User;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartDto {

    private Long id;

    private UserDto user;


    private ProductDto product;

}

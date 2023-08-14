package com.rf.ecommerce.Dto.Order;

import com.rf.ecommerce.Dto.Product.ProductDto;
import com.rf.ecommerce.Dto.User.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private ProductDto product;
    private UserDto user;
    private long timestap;
    private String orderStatus;
    private String address;
    private String city;
    private String district;
    private int postCode;
}

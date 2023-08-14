package com.rf.ecommerce.Dto.Order;

import com.rf.ecommerce.Entity.Product.Product;
import com.rf.ecommerce.Entity.User.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.security.PrivateKey;
@Data
public class RequestOrderDto {
    @NotEmpty
    private String address;
    @NotEmpty
    private String city;
    @NotEmpty
    private String district;
    private int postCode;
}

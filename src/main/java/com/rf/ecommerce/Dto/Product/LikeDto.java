package com.rf.ecommerce.Dto.Product;
import com.rf.ecommerce.Dto.User.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeDto {
    private Long id;
    private int count;
    private ProductDto product;
    private UserDto user;
}

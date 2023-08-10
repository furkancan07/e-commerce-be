package com.rf.ecommerce.Dto.Product;

import com.rf.ecommerce.Dto.User.UserDto;
import com.rf.ecommerce.Entity.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String content;
    private ProductDto product;
    private UserDto user;

}

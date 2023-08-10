package com.rf.ecommerce.Dto.Product;

import com.rf.ecommerce.Dto.Admin.AdminDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private AdminDto admin;
    private String image;
    private String category;
    private int price;

}

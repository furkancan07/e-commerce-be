package com.rf.ecommerce.Dto.Product;

import com.rf.ecommerce.Dto.Admin.AdminDto;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ProductDto {
    private Long id;
    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String title;

    @NotNull
    @NotEmpty
    @Size(min = 3)
    @Lob
    private String description;
    private AdminDto admin;
    @Lob
    private String image;
    private CategoryDto category;
    private int price;

}

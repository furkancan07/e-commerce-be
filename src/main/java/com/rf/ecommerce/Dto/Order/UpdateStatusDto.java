package com.rf.ecommerce.Dto.Order;

import jakarta.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UpdateStatusDto {
    @NotEmpty
    private String orderStatus;
}

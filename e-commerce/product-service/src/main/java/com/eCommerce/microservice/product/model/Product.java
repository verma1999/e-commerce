package com.eCommerce.microservice.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {
    @Id
    private String id;
    private String name;
    private String skuCode;
    private String description;
    private BigDecimal price;
}

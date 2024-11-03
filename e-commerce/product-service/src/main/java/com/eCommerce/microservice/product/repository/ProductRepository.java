package com.eCommerce.microservice.product.repository;

import com.eCommerce.microservice.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}

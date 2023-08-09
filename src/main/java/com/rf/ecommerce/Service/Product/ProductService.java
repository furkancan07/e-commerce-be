package com.rf.ecommerce.Service.Product;


import com.rf.ecommerce.Entity.Product.Product;
import com.rf.ecommerce.Repository.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ProductService {
    ProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public void save(Product product){
        productRepository.save(product);
    }
    public void delete(Long id){
      productRepository.deleteById(id);
    }
    public boolean existsById(Long id){
        return productRepository.existsById(id);
    }
    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow();
    }

    public List<Product> getAllProducts(){
        return  productRepository.findAll();
    }
}

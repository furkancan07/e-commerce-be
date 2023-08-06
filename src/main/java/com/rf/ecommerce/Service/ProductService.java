package com.rf.ecommerce.Service;


import com.rf.ecommerce.Entity.Product;
import com.rf.ecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class ProductService {
    ProductRepository productRepository;
    List<Product> productList=new ArrayList<>();

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public void save(Product product){
        productRepository.save(product);
    }
    public void delete(Long id){
        for(Product product:getAllProducts()){
            if(product.getId().equals(id)){
                productRepository.delete(product);
                productList.remove(product);
                break;
            }
        }
    }
    public boolean existsById(Long id){
        return productRepository.existsById(id);
    }
    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow();
    }

    public List<Product> getAllProducts(){
        return  productList;
    }
}

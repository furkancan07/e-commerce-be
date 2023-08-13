package com.rf.ecommerce.Controller.Product;

import com.rf.ecommerce.Dto.Product.ProductDto;
import com.rf.ecommerce.Entity.Admin.Admin;
import com.rf.ecommerce.Entity.Product.Product;
import com.rf.ecommerce.Service.Admin.AdminService;
import com.rf.ecommerce.Service.Product.ProductService;
import com.rf.ecommerce.Service.User.UserService;
import com.rf.ecommerce.error.ApiError;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    // ürün ekleme
    @PostMapping("/createProduct/{username}")
    @CrossOrigin
    public ResponseEntity<?> createProduct(@PathVariable String username , @Valid @RequestBody Product product){
              return productService.createToProduct(username,product);
    }
    // ürün silme
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
           return productService.deleteToProduct(id);
    }
    // ürün güncelleme
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,@Valid @RequestBody Product product){
            return productService.updateToProduct(id,product);
    }

    // bir admine ait tüm ürünleri getirme
    @GetMapping("/getProducts/{username}")
    public List<ProductDto> getProducts(@PathVariable String username){
        return  productService.getToProducts(username);
    }
    // kategoriye göre ürün getirme
    @GetMapping("/getCategoryProducts/{category}")
    public List<ProductDto> getCategoryProducts(@PathVariable String category){
        return productService.getToCategoryProducts(category);
    }


}

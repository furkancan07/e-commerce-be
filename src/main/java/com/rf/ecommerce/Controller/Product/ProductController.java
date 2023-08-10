package com.rf.ecommerce.Controller.Product;

import com.rf.ecommerce.Dto.Product.ProductDto;
import com.rf.ecommerce.Entity.Admin.Admin;
import com.rf.ecommerce.Entity.Product.Product;
import com.rf.ecommerce.Service.Admin.AdminService;
import com.rf.ecommerce.Service.Product.ProductService;
import com.rf.ecommerce.Service.User.UserService;
import com.rf.ecommerce.error.ApiError;
import jakarta.validation.Valid;
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
public class ProductController {
    @Autowired
    ProductService productService;
    // ürün ekleme
    @PostMapping("/createProduct/{username}")
    @CrossOrigin
    public ResponseEntity<?> createProduct(@PathVariable String username , @Valid @RequestBody Product product){
        boolean createToProduct=productService.createToProduct(username,product);
        if(createToProduct){
              return ResponseEntity.ok("Ürün eklendi");
           }
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kullanici bulunanadi");

    }
    // ürün silme
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        boolean deleteToProduct= productService.deleteToProduct(id);
       if(deleteToProduct){
           return ResponseEntity.ok("Başarı ile silindi");
       }
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paylaşım bulunamadi");

    }
    // ürün güncelleme
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,@Valid @RequestBody Product product){
        boolean updateToProduct= productService.updateToProduct(id,product);
        if(updateToProduct){
            return ResponseEntity.ok("Başari ile güncellendi");
        }
            ApiError apiError=new ApiError(404,"Paylaşım bulunamadi","/updateProduct");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError sendError(MethodArgumentNotValidException exception){
        ApiError apiError=new ApiError(400,"Hata","/api/product");
        Map<String,String> validationErross=new HashMap<>();
        for(FieldError fieldError: exception.getBindingResult().getFieldErrors()){
            validationErross.put(fieldError.getField(),fieldError.getDefaultMessage());
            apiError.setValidationErrors(validationErross);
        }
        return  apiError;
    }
}

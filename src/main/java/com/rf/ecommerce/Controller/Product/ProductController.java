package com.rf.ecommerce.Controller.Product;

import com.rf.ecommerce.Entity.Admin.Admin;
import com.rf.ecommerce.Entity.Product.Product;
import com.rf.ecommerce.Entity.User.User;
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
    AdminService adminService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    // ürün ekleme
    @PostMapping("/createProduct/{username}")
    @CrossOrigin
    public ResponseEntity<?> createProduct(@PathVariable String username , @Valid @RequestBody Product product){
        Admin admin=null;
        if(adminService.existsByUsername(username)){
               admin=adminService.findByUsername(username);
               product.setAdmin(admin);
               productService.save(product);
               productService.getAllProducts().add(product);
              return ResponseEntity.ok("Ürün eklendi");
           }
        else {
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kullanici bulunanadi");
           }
    }
    // ürün silme
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
       if(productService.existsById(id)){
           productService.delete(id);
           return ResponseEntity.ok("Başarı ile silindi");
       }
       else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paylaşım bulunamadi");
       }
    }
    // ürün güncelleme
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,@Valid @RequestBody Product product){
        Product product1=null;
        if(productService.existsById(id)){
            product1=productService.findById(id);
            product1.setTitle(product.getTitle());
            product1.setDescription(product.getDescription());
            productService.save(product1);
            productService.getAllProducts().set(id.intValue()-1,product);
            return ResponseEntity.ok("Başari ile güncellendi");
        }else{
            ApiError apiError=new ApiError(404,"Paylaşım bulunamadi","/updateProduct");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
        }

    }

    // bir admine ait tüm ürünleri getirme
    @GetMapping("/getProducts/{username}")
    public List<Product> getProducts(@PathVariable String username){
        List<Product> productList=new ArrayList<>();
        for(Product product: productService.getAllProducts()){
            if(product.getAdmin().getUsername().equals(username)){
                productList.add(product);
            }
        }
        return  productList;
    }
    // kategoriye göre ürün getirme
    @GetMapping("/getCategoryProducts/{category}")
    public List<Product> getCategoryProducts(@PathVariable String category){
        List<Product> productList=new ArrayList<>();
        for(Product product : productService.getAllProducts()){
            if(product.getCategory().equals(category)){
                productList.add(product);
            }
        }
        return productList;
    }




    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError hataGonder(MethodArgumentNotValidException exception){
        ApiError apiError=new ApiError(400,"Hata","/api/product");
        Map<String,String> validationErross=new HashMap<>();
        for(FieldError fieldError: exception.getBindingResult().getFieldErrors()){
            validationErross.put(fieldError.getField(),fieldError.getDefaultMessage());
            apiError.setValidationErrors(validationErross);
        }
        return  apiError;
    }


}

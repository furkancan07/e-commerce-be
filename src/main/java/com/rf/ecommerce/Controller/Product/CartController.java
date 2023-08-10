package com.rf.ecommerce.Controller.Product;

import com.rf.ecommerce.Dto.Product.CartDto;
import com.rf.ecommerce.Entity.Product.Cart;
import com.rf.ecommerce.Service.Product.CartService;
import com.rf.ecommerce.Service.Product.ProductService;
import com.rf.ecommerce.Service.User.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    CartService cartService;

    // sepete ekleme
    @PostMapping("/addCart/{email}/{productId}")
    @CrossOrigin
    public ResponseEntity<?> addCart(@PathVariable String email,@PathVariable Long productId){
        boolean addToCart= cartService.addToCart(email,productId);
        if(addToCart){
           return  ResponseEntity.ok("Ürün Sepete Eklendi");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kullanici veya Paylaşım bulunamadı");
    }

    // sepetten kaldirma
    @DeleteMapping("/deleteCart/{cartId}")
    @CrossOrigin
    public ResponseEntity<?> deleteHamper(@PathVariable Long cartId){
        boolean deleteToCart= cartService.deleteToCart(cartId);
        if(deleteToCart){
            return ResponseEntity.ok("Sepetten ürün kaldırıldı");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ürün bulunamadı");
    }


    //sepettekilerin listesini getirme
    @GetMapping("/getCarts/{email}")
    @CrossOrigin
    public List<CartDto> getCarts(@PathVariable String email){
        return cartService.getCarts(email);
    }

}

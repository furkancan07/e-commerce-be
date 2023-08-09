package com.rf.ecommerce.Controller.Product;

import com.rf.ecommerce.Entity.Product.Cart;
import com.rf.ecommerce.Entity.Product.Product;
import com.rf.ecommerce.Entity.User.User;
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
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    // sepete ekleme
    @PostMapping("/addCart/{email}/{productId}")
    @CrossOrigin
    public ResponseEntity<?> addCart(@PathVariable String email,@PathVariable Long productId){
        if(userService.existsByEmail(email) && productService.existsById(productId)){
            Cart cart =new Cart();

            User user=userService.findByEmail(email);
            Product product=productService.findById(productId);
            cart.setUser(user);
            cart.setProduct(product);

            cartService.save(cart);

           return  ResponseEntity.ok("Ürün Sepete Eklendi");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kullanici veya Paylaşım bulunamadı");
    }

    // sepetten kaldirma
    @DeleteMapping("/deleteCart/{hamperId}")
    @CrossOrigin
    public ResponseEntity<?> deleteHamper(@PathVariable Long hamperId){
        if(cartService.existsById(hamperId)){
            Cart cart = cartService.findById(hamperId);
            cartService.delete(hamperId);

            return ResponseEntity.ok("Sepetten ürün kaldırıldı");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ürün bulunamadı");
    }


    //sepettekilerin listesini getirme
    @GetMapping("/getCarts/{email}")
    @CrossOrigin
    public List<Cart> getHampers(@PathVariable String email){
        List<Cart> carts =new ArrayList<>();

        for(Cart cart : cartService.getAllHampers()){
            if(cart.getUser().getEmail().equals(email)){
                carts.add(cart);
            }
        }
        return carts;
    }

}
